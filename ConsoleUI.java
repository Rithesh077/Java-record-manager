
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final RecordManager recordManager;
    private final Scanner scanner;
    private final String filename;

    public ConsoleUI(RecordManager recordManager, String filename) {
        this.recordManager = recordManager;
        this.filename = filename;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            showMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 ->
                    addRecord();
                case 2 ->
                    getRecordById();
                case 3 ->
                    listAllRecords();
                case 4 ->
                    saveRecords();
                case 5 ->
                    deleteRecord();
                case 6 ->
                    updateRecord();
                case 7 ->
                    exit();
                default ->
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\nRecord Management System:");
        System.out.println("1. Add Record");
        System.out.println("2. Get Record by ID");
        System.out.println("3. List All Records");
        System.out.println("4. Save Records");
        System.out.println("5. Delete Record by ID");
        System.out.println("6. Update Record with Password");
        System.out.println("7. Exit");
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void addRecord() {
        try {
            System.out.print("Enter the record type (simple/analyzed): ");
            String type = scanner.nextLine().toLowerCase();

            System.out.print("Enter Record ID: ");
            long id = Long.parseLong(scanner.nextLine());

            System.out.print("Enter Record Details: ");
            String details = scanner.nextLine();

            System.out.print("Set a Password for this Record: ");
            String password = scanner.nextLine();

            Record newRecord = switch (type) {
                case "simple" ->
                    new SimpleRecord(id, details, password);
                case "analyzed" ->
                    new AnalyzedTextRecord(id, details, password);
                default -> {
                    System.out.println("Unknown record type.");
                    yield null;
                }
            };

            if (newRecord != null) {
                recordManager.addRecord(newRecord);
                System.out.println("Record added successfully!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void getRecordById() {
        try {
            System.out.print("Enter Record ID to retrieve: ");
            long id = Long.parseLong(scanner.nextLine());
            Record record = recordManager.getRecord(id);

            if (record != null) {
                System.out.println("ID: " + record.getId());
                System.out.println("Details: " + record.getDetails());
            } else {
                System.out.println("Record not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    private void listAllRecords() {
        List<Record> allRecords = recordManager.getAllRecords();
        if (allRecords.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (Record r : allRecords) {
                System.out.println("ID: " + r.getId() + ", Details: " + r.getDetails());
            }
        }
    }

    private void saveRecords() {
        recordManager.saveToFile(filename);
    }

    private void deleteRecord() {
        try {
            System.out.print("Enter Record ID to delete: ");
            long id = Long.parseLong(scanner.nextLine());
            boolean deleted = recordManager.deleteRecord(id);
            System.out.println(deleted ? "Record deleted." : "Record not found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    private void updateRecord() {
        try {
            System.out.print("Enter Record ID to update: ");
            long id = Long.parseLong(scanner.nextLine());

            System.out.print("Enter new Record Details: ");
            String newDetails = scanner.nextLine();

            System.out.print("Enter Record Password: ");
            String password = scanner.nextLine();

            String result = recordManager.updateDetails(id, newDetails, password);
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    private void exit() {
        try (scanner) {
            System.out.println("Exiting...");
        }
        System.exit(0);
    }
}
