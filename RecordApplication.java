
import java.util.List;
import java.util.Scanner;

public class RecordApplication {

    private static final String FILENAME = "records.csv";

    public static void main(String[] args) {
        RecordManager recordManager = new RecordManager();
        recordManager.loadFromFile(FILENAME);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nRecord Management System:");
            System.out.println("1. Add Record");
            System.out.println("2. Get Record by ID");
            System.out.println("3. List All Records");
            System.out.println("4. Save your record");
            System.out.println("5. Delete Record by ID");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                String inputLine = scanner.nextLine();
                choice = Integer.parseInt(inputLine);
            } catch (NumberFormatException e) {

                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    try {
                        System.out.print("Enter Record ID: ");
                        long id = Long.parseLong(scanner.nextLine());

                        System.out.print("Enter Record Details: ");
                        String details = scanner.nextLine();
                        recordManager.addRecord(new SimpleRecord(id, details));
                        System.out.println("Record added successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format. Please enter a valid number.");
                    }
                    break;
                }
                case 2 -> {
                    try {
                        System.out.print("Enter Record ID to retrieve: ");
                        long retrieveId = Long.parseLong(scanner.nextLine());

                        Record record = recordManager.getRecord(retrieveId);
                        if (record != null) {
                            System.out.println("\nRetrieved Record:");
                            System.out.println("ID: " + record.getId());
                            System.out.println("Details: " + record.getDetails());
                        } else {
                            System.out.println("Record not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format. Please enter a valid number.");
                    }
                    break;
                }
                case 3 -> {
                    System.out.println("\nAll Records:");
                    List<Record> allRecords = recordManager.getAllRecords();
                    if (allRecords.isEmpty()) {
                        System.out.println("No records found.");
                    } else {
                        for (Record r : allRecords) {
                            System.out.println("ID: " + r.getId() + ", Details: " + r.getDetails());
                        }
                    }
                    break;
                }
                case 4 -> {
                    recordManager.saveToFile(FILENAME);
                    System.out.println("Records saved to " + FILENAME);
                    break;
                }
                case 5 -> {
                    try {
                        System.out.print("Enter Record ID to delete: ");
                        long deleteId = Long.parseLong(scanner.nextLine());

                        boolean deleted = recordManager.deleteRecord(deleteId);
                        if (deleted) {
                            System.out.println("Record deleted successfully.");
                        } else {
                            System.out.println("Record not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format. Please enter a valid number.");
                    }
                    break;
                }
                case 6 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default ->
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
