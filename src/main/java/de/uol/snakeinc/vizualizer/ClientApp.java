package de.uol.snakeinc.vizualizer;

import de.uol.snakeinc.vizualizer.gui.Board;
import de.uol.snakeinc.vizualizer.gui.FileChooserDialog;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class ClientApp extends Application {

    Board board;
    HBox root = new HBox();
    ComboBox speedBox;
    Button convertButton;
    FileChooserDialog fileChooser;
    ListView<Text> usersView = new ListView<>();
    ObservableList<Text> users = FXCollections.observableArrayList();
    Boolean isActive = false;
    Pane roundsPane;

    static List<Color> colors = List.of(
        Color.YELLOW,
        Color.RED,
        Color.BLUE,
        Color.ORANGE,
        Color.GREEN,
        Color.LIME,
        Color.PURPLE,
        Color.WHITE
    );

    public void initialize() {
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setSpacing(80);
        ObservableList<String> speedChoices =
            FXCollections.observableArrayList(
                "100",
                "200",
                "1000",
                "2000"
            );
        speedBox = new ComboBox(speedChoices);
        convertButton = new Button();
        convertButton.setText("Start the Game");
        convertButton.setOnMouseClicked(e->{
            if (fileChooser.checkFile()) {
                setUpBoard(fileChooser.getActualFile());
                board.startGame();
                initializeUserList();
                initializeRoundsField();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Load a file and try again!");
                alert.show();
            }
        });
        roundsPane = new Pane();
        roundsPane.setMinHeight(50);
        roundsPane.setMinWidth(100);
        usersView.setItems(users);
    }

    public void setUpBoard(File file) {
        if (isActive) {
            root.getChildren().remove(board);
        }
        board = new Board(file);
        root.getChildren().add(board);
        board.setSpeed(Integer.parseInt(speedBox.getValue().toString()));
        speedBox.setOnHiding(e -> {if(isActive) board.setSpeed(Integer.parseInt(speedBox.getValue().toString()));});
    }

    public void initializeUserList() {
        if (isActive) {
            users.clear();
        }
        for (Integer id : board.getGame().getPlayers().keySet()) {
            String name = board.getGame().getPlayers().get(id);
            Text text = new Text(name);
            text.setFill(colorBag(id));
            users.add(text);
        }
    }

    public void initializeRoundsField() {
        if (isActive) {
            roundsPane.getChildren().clear();
        }
        Text rounds = new Text();
        board.actualRoundProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                rounds.setText("Runde: " + board.getActualRound());
            }
        });
        roundsPane.getChildren().add(rounds);
    }


    @Override
    public void start(Stage stage) {
        isActive = true;
        initialize();

        fileChooser = new FileChooserDialog(stage);
        fileChooser.getChildren().addAll(convertButton, speedBox, usersView,roundsPane);
        fileChooser.setPadding(new Insets(10,10,10,10));
        fileChooser.setSpacing(20);

        root.getChildren().add(fileChooser);
        Scene scene = new Scene(root, 1500, 1000);
        stage.setScene(scene);
        stage.show();
    }

    public static Color colorBag(int index) {

        if (index <= 0) {
            return Color.BLACK;
        }
        return colors.get(index);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
