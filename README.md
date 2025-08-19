# Java CLI Record Manager

## Overview

A simple command-line application built in Java to manage a collection of records. This project was created as a step-by-step exercise to learn and apply core Object-Oriented Programming (OOP) principles from first principles.

This application provides a text-based interface for a user to perform full CRUD (Create, Read, Update, Delete) operations. Each record consists of a unique ID and textual details. The program also features data persistence, saving all records to a local `records.csv` file.

## Features

- **Interactive Menu**: A simple and robust command-line menu to navigate the application's features.
- **Add Records**: Create new records with a unique ID and details.
- **View Records**: Retrieve a specific record by its ID or list all records.
- **Update Records**: Find an existing record by its ID and modify its details.
- **Delete Records**: Find and remove a specific record by its ID.
- **Data Persistence**: Records are automatically loaded from `records.csv` on startup and can be explicitly saved back to the file, ensuring data is not lost when the program closes.
- **Data Validation**: The program validates user details based on rules such as non empty details without commas and non empty and strong password preventing bad data from entering the system.

```csv
id,details,password
```

## Core Concepts Demonstrated

**Object-Oriented Programming (OOP)**:

- **Encapsulation**: Hiding the internal data of objects (`private` fields) and providing public methods for controlled access (e.g., `RecordManager` hiding its `ArrayList`).
- **Abstraction**: Using an `interface` (`Record`) to define a contract for what a record must be able to do, separating the "what" from the "how."
- **Polymorphism**: The `RecordManager` works with the `Record` interface, allowing it to manage different types of records without changing its own code.

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

## Future Enhancements

- **User Authentication**: Adding a password verification system to protect sensitive operations like updating and deleting records. Future versions will also secure stored passwords using hashing instead of plain text.
- **Data Transfer Object (DTO) Pattern**: Implementing a DTO class for user input (e.g., for creating and updating records). This would decouple the internal data model from the data structure used by the user interface, which is a common best practice in larger applications.
- **New Record Type**: Implementing a new class (e.g., `AnalyzedTextRecord`) that also implements the `Record` interface but adds new functionality, demonstrating polymorphism.
- **Structured ID**: Implementing a new class or system for more complex and meaningful record IDs.
- **Enhanced Error Handling**: Providing more specific feedback for different types of I/O errors.
- **Dynamic Filenames**: Prompt the user to specify a file name when loading and saving the records instead of using hardcoded names.
