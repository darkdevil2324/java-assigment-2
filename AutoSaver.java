import java.io.IOException;
import java.nio.file.Path;

// Multithreading: background thread that saves data every few seconds
public class AutoSaver extends Thread {
    private final Library library;
    private final Path file;

    public AutoSaver(Library library, Path file) {
        this.library = library;
        this.file = file;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
                library.save(file);
            } catch (InterruptedException | IOException e) {
                return;
            }
        }
    }
}
