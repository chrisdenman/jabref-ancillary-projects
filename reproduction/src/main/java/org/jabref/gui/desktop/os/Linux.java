package org.jabref.gui.desktop.os;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Path;

import org.jabref.logic.util.HeadlessExecutorService;

public class Linux {

    public static void nativeOpenFile(String filePath) {
        HeadlessExecutorService.INSTANCE.execute(() -> {
            try {
                Desktop.getDesktop().open(Path.of(filePath).toFile());
                System.out.println("Open file in default application with Desktop integration");
            } catch (IllegalArgumentException e) {
                System.out.println("Fail back to xdg-open");
                try {
                    String[] cmd = {"xdg-open", filePath};
                    Runtime.getRuntime().exec(cmd);
                } catch (Exception e2) {
                    System.out.println("Open operation not successful: ");
                    System.out.println(e2);
                }
            } catch (IOException e) {
                System.out.println("Native open operation not successful: ");
                System.out.println(e);
            } catch (Throwable t) {
                System.out.println("Uncaught throwable");
                System.out.println(t);
            }
            System.out.println("nativeOpenFile exit.");
        });
    }
}
