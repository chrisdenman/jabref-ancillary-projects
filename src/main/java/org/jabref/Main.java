package org.jabref;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// https://github.com/javafxports/openjdk-jfx/issues/620
public class Main extends Application {

    public void start(Stage primaryStage) {
        TextArea ta1 = new TextArea();
        TextArea ta2 = new TextArea();

        MenuItem item1 = new MenuItem("Insert \"Hello\"");
        item1.setOnAction((ActionEvent) -> ta1.setText("Hello"));
        item1.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));

        MenuItem item2 = new MenuItem("Insert \"World!\"");
        item2.setOnAction((ActionEvent) -> ta2.setText("World!"));
        item2.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));

        ContextMenu context1 = new ContextMenu();
        context1.getItems().add(item1);

        ContextMenu context2 = new ContextMenu();
        context2.getItems().add(item2);

        ta1.setContextMenu(context1);
        ta2.setContextMenu(context2);

        VBox vBox = new VBox();
        vBox.getChildren().add(ta1);
        vBox.getChildren().add(ta2);

        Scene scene = new Scene(vBox, 300, 300);

        primaryStage.setTitle("Accelerator Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}