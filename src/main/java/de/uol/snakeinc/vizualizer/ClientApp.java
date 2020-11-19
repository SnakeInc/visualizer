package de.uol.snakeinc.vizualizer;

import de.uol.snakeinc.vizualizer.gui.Board;
import de.uol.snakeinc.vizualizer.gui.FileChooserDialog;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class ClientApp extends Application {

    Board board;
    HBox root = new HBox();
    ComboBox speedBox;
    Button convertButton;
    FileChooserDialog fileChooser;
    ListView<Text> usersView = new ListView<>();
    ObservableList<Text> users = FXCollections.observableArrayList();
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
                board = new Board(fileChooser.getActualFile());
                board.setSpeed(Integer.parseInt(speedBox.getValue().toString()));
                root.getChildren().add(board);
                board.startGame();
                initializeUserList();}
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Load a file and try again!");
                alert.show();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void initializeUserList() {


        for (Integer id : board.getGame().getPlayers().keySet()) {
            String name = board.getGame().getPlayers().get(id);
            Text text = new Text(name);
            text.setFill(colorBag(id));
            users.add(text);
        }
    }


    @Override
    public void start(Stage stage) {
        initialize();

        usersView.setItems(users);
        fileChooser = new FileChooserDialog(stage);
        fileChooser.getChildren().addAll(convertButton, speedBox, usersView);

        root.getChildren().add(fileChooser);
        Scene scene = new Scene(root, 1500, 1500);
        stage.setScene(scene);
        stage.show();
    }

    public static Color colorBag(int index) {

        if (index <= 0) {
            return Color.BLACK;
        }
        return colors.get(index);
    }
}
