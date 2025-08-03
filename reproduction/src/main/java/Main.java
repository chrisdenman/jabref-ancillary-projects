import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.jabref.gui.desktop.os.Linux;

public class Main extends Application {

    private static String pdfPath;

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Open PDF");
        btn.setOnAction(ignored -> Linux.nativeOpenFile(pdfPath));
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 100, 50);
        primaryStage.setTitle("Open PDF Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        pdfPath = args[0];
        launch(args);
    }
}