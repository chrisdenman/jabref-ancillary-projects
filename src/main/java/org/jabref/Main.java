package org.jabref;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Web View");
        stage.setScene(new Scene(new Browser(), 750, 500, Color.web("#666970")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
