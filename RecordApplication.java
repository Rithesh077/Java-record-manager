
import java.io.IOException;

public class RecordApplication {

    private static final String FILENAME = "records.csv";

    public static void main(String[] args) throws IOException {
        RecordManager recordManager = new RecordManager();
        recordManager.loadFromFile(FILENAME);

        ConsoleUI ui = new ConsoleUI(recordManager, FILENAME);
        ui.start();
    }
}
