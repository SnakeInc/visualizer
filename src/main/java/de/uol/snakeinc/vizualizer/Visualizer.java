package de.uol.snakeinc.vizualizer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Visualizer extends Application {

    Board board;
    BorderPane root = new BorderPane();
    ComboBox speedBox;
    Button convertButton;
    FileChooserDialog fileChooser;
    ListView<Text> usersView = new ListView<>();
    ObservableList<Text> users;



    public static void main(String[] args) {
        launch(args);
    }

    public void initialize() {
        root.setPadding(new Insets(20, 20, 20, 20));
        ObservableList<String> speedChoices =
            FXCollections.observableArrayList(
                "0.3",
                "0.5",
                "1",
                "2"
            );
        speedBox = new ComboBox(speedChoices);
        convertButton = new Button();
        convertButton.setText("Start the Game");
        convertButton.setOnMouseClicked(e->{
            if (fileChooser.checkFile()) {
            board = new Board(fileChooser.getActualFile());
            board.setSpeed(Double.parseDouble(speedBox.getValue().toString()));
            board.startGame();
            initializeUserList();}
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Load a file and try again!");
                alert.show();
            }
        });
    }

    public void initializeUserList() {
        board.getGame().getPlayers();
        usersView.setItems(users);
    }


    @Override
    public void start(Stage stage) {
        initialize();

        fileChooser = new FileChooserDialog(stage);
        fileChooser.getChildren().addAll(convertButton, speedBox, usersView);

        root.setLeft(fileChooser);
        root.setRight(board);
        Scene scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }
}
