
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RecordManager {
// TODO: Implement password hashing instead of storing plain text
// TODO: Support multi-line input for Details in CLI
// TODO: Fully comply with CSV standard (RFC 4180) for quotes, commas, and newlines
// TODO: Improve handling of unknown record types when loading CSV
// TODO: Add proper logging instead of using System.out.println
// TODO: Consider validating/analyzing Details fields for AnalyzedTextRecord like SimpleRecord

    private final List<Record> records;

    public RecordManager() {
        records = new ArrayList<>();
    }

    public void addRecord(Record record) {
        boolean exists = records.stream().anyMatch(r -> r.getId() == record.getId());
        if (exists) {
            throw new IllegalArgumentException("A record with ID " + record.getId() + " already exists.");
        }
        records.add(record);
    }

    public Record getRecord(long ID) {
        return records.stream()
                .filter(record -> record.getId() == ID)
                .findFirst()
                .orElse(null);
    }

    public List<Record> getAllRecords() {
        return records;
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

    public void loadFromFile(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));

        for (int lineNum = 0; lineNum < lines.size(); lineNum++) {
            String line = lines.get(lineNum);
            List<String> parts = parseCsvLine(line);

            if (parts.size() != 4) {
                System.out.println("Skipping malformed line " + (lineNum + 1) + ": " + line);
                continue;
            }

            try {
                String type = parts.get(0);
                long id = Long.parseLong(parts.get(1));
                String details = parts.get(2);
                String password = parts.get(3);

                Record newRecord = switch (type) {
                    case "Simple" ->
                        new SimpleRecord(id, details, password);
                    case "Analyzed" ->
                        new AnalyzedTextRecord(id, details, password);
                    default ->
                        null;
                };

                if (newRecord != null) {
                    this.records.add(newRecord);
                } else {
                    System.out.println("Unknown record type on line " + (lineNum + 1) + ": " + type);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format on line " + (lineNum + 1) + ": " + line);
            } catch (IllegalArgumentException e) {
                System.out.println("Error creating record on line " + (lineNum + 1) + ": " + e.getMessage());
            }
        }

        System.out.println("Successfully loaded " + records.size() + " records from " + filename);
    }

    public static String escapeCsv(String input) {
        if (input.contains(",") || input.contains("\"") || input.contains("\n")) {
            input = input.replace("\"", "\"\"");
            return "\"" + input + "\"";
        }
        return input;
    }

    private List<String> parseCsvLine(String line) {
        List<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                parts.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        parts.add(current.toString().trim());
        return parts;
    }

    public static void validateDetails(String details) {
        if (details == null || details.trim().isEmpty()) {
            throw new IllegalArgumentException("Details cannot be null or empty.");
        }
    }
}
