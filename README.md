# Java CLI Record Manager

## Overview

A simple command-line application built in Java to manage a collection of records. This project was created as a step-by-step exercise to learn and apply core Object-Oriented Programming (OOP) principles.

This application provides a text-based interface for a user to perform full CRUD (Create, Read, Update, Delete) operations. Each record consists of a unique ID and textual details. The program also features data persistence, saving all records to a local `records.csv` file.

## Features

- **Interactive Menu**: A simple and robust command-line menu to navigate the application's features.
- **Record Types** :
  - `SimpleRecord`: Basic record with details and password validation.
  - `AnalyzedTextRecord`: Automatically analyzes details (word count, character count, vowel count).
- **Add Records**: Create new records with a unique ID and details.
- **View Records**: Retrieve a specific record by its ID or list all records.
- **Update Records**: Find an existing record by its ID and modify its details.(password-protected)
- **Delete Records**: Find and remove a specific record by its ID.
- **Data Persistence**: Records are automatically loaded from `records.csv` on startup and can be explicitly saved back to the file, ensuring data is not lost when the program closes.
- **Data Validation**:
  - `SimpleRecord`: Ensures strong password (≥8 chars, includes uppercase, lowercase, digit, special character) and prevents commas in details.
  - `AnalyzedTextRecord`: Performs text analysis automatically.
    Example`CSV`format:
- **Duplicate ID prevention**: Adding a record with existing ID is not allowed.
- **Unified Validation**: Both Simple and Analyzed Record types use a common Validation to prevent empty of null details.

```csv
Simple,101,Meeting at 10am,Strong@123
Analyzed,102,Hello World,MyPass@2025
```

## Core Concepts Demonstrated

**Object-Oriented Programming (OOP)**:

- **Encapsulation**: Hiding the internal data of objects (`private` fields) and providing public methods for controlled access (e.g., `RecordManager` hiding its `ArrayList`).
- **Abstraction**: Using an `interface` (`Record`) to define a contract for what a record must be able to do, separating the "what" from the "how."
- **Polymorphism**: `RecordManager` can manage both `SimpleRecord` and `AnalyzedTextRecord`.

**Java Fundamentals**:

- Classes, Objects, and Interfaces
- Constructors and the `this` keyword
- **File I/O**: Using `java.nio.file.Files` for modern and efficient reading/writing to a local file.
- **Exception Handling**: Using `try-catch` blocks (`NumberFormatException`, `IOException`) to create a robust and crash-proof user experience.
- The `Scanner` class for interactive user input.
- `ArrayList` for managing a dynamic collection of objects.

## Usage Instructions

**Prerequisites**

- Java Development Kit (JDK) 11 or higher installed on your system.

**Running the Application**

1.  Clone the repository to your local machine.
    ```bash
    git clone https://github.com/Rithesh077/Java-record-manager
    ```
2.  Navigate to the project directory.
    ```bash
    cd Java-record-manager
    ```
3.  Compile all the Java source files.
    ```bash
    javac *.java
    ```
4.  Run the main application class.
    ```bash
    java RecordApplication
    ```
5.  Follow the on-screen instructions to interact with the record manager.

## Known Limitations

- `SimpleRecord` forbids commas in details to simplify CSV parsing.
- `AnalyzedTextRecord` allows commas, which can potentially break CSV parsing in certain edge cases.
- Passwords are stored as plain text in the CSV file.
- CSV parsing assumes exactly 4 fields per line; malformed lines are skipped.
- No hashing or encryption is applied to passwords.
- Input validation for `AnalyzedTextRecord` is minimal; any text is accepted.

## Future Enhancements

- **Improved CSV Handling**:
- Escape/quote values with commas instead of rejecting them.
- Consider JSON or XML as alternative storage formats.
- **Password Security**: Replace plain text storage with hashing (`SHA-256`, `BCrypt`).
- **Search Feature**: Search records by keyword in details.
- **Dynamic Filenames**: Allow user to specify the save/load file.
- **Refactored Design**: Move input handling (`Scanner`) into a dedicated `ConsoleUI` class.
- **Unique IDs**: Replace manual IDs with auto-generated UUIDs.
- **Export Options**: Add JSON/Excel export for better integration.

## Future Direction (Advanced)

A potential evolution of this project is to expand the concept of `Record` into a more generic `DataObject`, capable of handling various types of structured and unstructured data:

- Tabular data (CSV, Excel)
- Semi-structured data (JSON, XML)
- Binary formats (images, audio, video)

Such a system would require multiple parsers, storage backends, and abstractions for heterogeneous data.
While outside the scope of this repository, this direction illustrates how the principles demonstrated here (encapsulation, abstraction, polymorphism, persistence) can scale into larger data management frameworks.
