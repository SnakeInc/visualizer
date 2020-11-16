package de.uol.snakeinc.vizualizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Visualizer extends Application {

    Board board;
    BorderPane root = new BorderPane();

    public static void main(String[] args) {
        launch(args);
    }

    public void initialize() {
        root.setPadding(new Insets(20, 20, 20, 20));
    }


    @Override
    public void start(Stage stage) {
        initialize();


        FileChooserDialog fileChooser = new FileChooserDialog(stage);
        Button convertButton = new Button();
        convertButton.setText("Start the Game");
        convertButton.setOnMouseClicked(e->{
            board = new Board(fileChooser.getActualFile());
        });
        fileChooser.getChildren().addAll(convertButton);
        root.setLeft(fileChooser);
        root.setRight(board);
        Scene scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();
    }
}
