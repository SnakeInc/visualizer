package de.uol.snakeinc.vizualizer.gui;

import java.io.File;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChooserDialog extends VBox {

    private File actualFile;
    private FileChooser fileChooser = null;
    private static SimpleObjectProperty<File> lastKnownDirectoryProperty = new SimpleObjectProperty<>();

    public FileChooserDialog(Stage stage) {
        fileChooser = getInstance();
        //String homePath = System.getProperty("user.home");
        //File defaultDirectory = new File(homePath + "/Downloads");
        //fileChooser.setInitialDirectory(defaultDirectory);
        final Button openButton = new Button("Open a Game...");

        openButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        openFile(file);
                        lastKnownDirectoryProperty.setValue(file.getParentFile());
                    }
                }
            });

        final GridPane inputGridPane = new GridPane();

        GridPane.setConstraints(openButton, 0, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openButton);
        this.getChildren().addAll(inputGridPane);
        this.setPadding(new Insets(12, 12, 12, 12));
    }

    private FileChooser getInstance(){
        if(fileChooser == null) {
            fileChooser = new FileChooser();
            fileChooser.initialDirectoryProperty().bindBidirectional(lastKnownDirectoryProperty);
        }
        return fileChooser;
    }

    private void openFile(File file) {
            actualFile = file;
    }

    public File getActualFile() {
        return actualFile;
    }

    public Boolean checkFile() {
        return actualFile != null;
    }
}
