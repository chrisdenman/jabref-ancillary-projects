import org.jabref.gui.desktop.os.Linux;
import org.jabref.logic.util.HeadlessExecutorService;

public class Main {
    public static void main(String[] args) {
        Linux.nativeOpenFile(args[0]);
        HeadlessExecutorService.INSTANCE.shutdownEverything();
    }
}
