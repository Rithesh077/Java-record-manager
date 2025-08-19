
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RecordManager {

    private final List<Record> records;

    public RecordManager() {
        records = new ArrayList<>();
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    public Record getRecord(long ID) {
        for (Record record : records) {
            if (record.getId() == ID) {
                return record;
            }
        }
        return null;
    }

    public List<Record> getAllRecords() {
        return records;
    }

    public String toCsv() {
        StringBuilder csvBuilder = new StringBuilder();
        for (Record record : records) {
            csvBuilder.append(record.getId()).append(",").append(record.getDetails()).append("\n");
        }
        return csvBuilder.toString();
    }

    public boolean deleteRecord(long ID) {
        return records.removeIf(record -> record.getId() == ID);
    }

    public String updateDetails(long ID, String newDetails, String password) {
        Record record = getRecord(ID);
        if (record == null) {
            return "Record not found.";
        }
        if (record.updateDetails(newDetails, password)) {
            return "Record updated successfully.";
        }
        return "Invalid password.";
    }

    public void saveToFile(String filename) {
        List<String> csvLines = new ArrayList<>();
        for (Record record : this.records) {
            csvLines.add(record.toCsv());
        }

        try {
            Files.write(Paths.get(filename), csvLines);
            System.out.println("Records successfully saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving records to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("UnnecessaryContinue")
    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("No existing data file found. Starting with an empty database.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.out.println("Skipping malformed record line: " + line);
                    continue; // skip bad line
                }

                try {
                    long id = Long.parseLong(parts[0]);
                    String details = parts[1];
                    String password = parts[2];
                    this.records.add(new SimpleRecord(id, details, password));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping record with invalid ID: " + line);
                }
            }
            System.out.println("Successfully loaded " + records.size() + " records from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading records from file: " + e.getMessage());
        }
    }

}
