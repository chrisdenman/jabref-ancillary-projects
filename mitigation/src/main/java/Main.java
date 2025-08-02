import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.jabref.logic.util.HeadlessExecutorService;

public class Main {
    public static void main(String[] args) {
        linuxNativeOpenFile(new File(args[0]));
    }

    public static void linuxNativeOpenFile(final File file) {
        HeadlessExecutorService.INSTANCE.execute(() -> {
            if (!file.exists()) {
                throw new RuntimeException("File does not exist: " + file);
            }

            // Try Desktop API
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                try {
                    System.out.println("Calling Desktop.open.");
                    Desktop.getDesktop().open(file);
                    return;
                } catch (IOException | UnsupportedOperationException e) {
                    handleError(e);
                }
            } else {
                System.out.println("Desktop.open not supported.");
            }

            // Try Linux openers
            final String absoluteFilePath = file.getAbsolutePath();
            final String[][] fallbackCommands = {
                    {"xdg-open", absoluteFilePath}, // Most common
                    {"gio", "open", absoluteFilePath}, // GNOME
                    {"kioclient5", "exec", absoluteFilePath}, // KDE
                    {"exo-open", absoluteFilePath} // XFCE
            };

            for (String[] fallbackCommand : fallbackCommands) {
                try {
                    final File tempFile = File.createTempFile("jabRefDesktopOpenFallback", ".txt");
                    tempFile.deleteOnExit();
                    System.out.println("Writing output to " + tempFile.getCanonicalPath());
                    final ProcessBuilder pv = new ProcessBuilder(fallbackCommand);
                    System.out.println("Calling " + String.join(" ", pv.command()));

                    pv.redirectErrorStream(true);
                    pv.redirectOutput(tempFile);
                    final Process p = pv.start();
                    System.out.println("Process exit code=" + p.waitFor());
                    return;
                } catch (IOException | InterruptedException e) {
                    handleError(e);
                }
            }

            throw new RuntimeException("Unable to open file: no supported method found.");
        });
    }

    private static void handleError(final Throwable e) {
        System.out.println(e.toString());
        e.fillInStackTrace();
        //noinspection CallToPrintStackTrace
        e.printStackTrace();
    }
}
