# Java CLI Record Manager

## Overview

A simple command-line application built in Java to manage a collection of records. This project was created as a step-by-step exercise to learn and apply core Object-Oriented Programming (OOP) principles from first principles.

This application provides a text-based interface for a user to perform basic CRUD (Create, Read, Update, Delete) operations on an in-memory database of records. Each record consists of a unique ID and textual details. The project is structured to be clear, maintainable, and demonstrate good software design practices.

## Features

1. **Interactive Menu**: A simple command-line menu to navigate the application's features.

2. **Add Records**: Create new records with a unique ID and details.

3. **View Records**: Retrieve a specific record by its ID or list all records currently in the database.

4. **Dynamic & In-Memory**: The application runs entirely in memory; data is not saved to a file.

## Core Concepts Demonstrated

**Object-Oriented Programming (OOP)**:

- Encapsulation: Hiding the internal data of objects (private fields) and providing public methods for controlled access (e.g., RecordManager hiding its ArrayList).

- Abstraction: Using an interface (Record) to define a contract for what a record must be able to do, separating the "what" from the "how."

- Polymorphism: The RecordManager works with the Record interface, allowing it to manage different types of records (like SimpleRecord or a future AnalyzedTextRecord) without changing its own code.

**Java Fundamentals**:

- Classes and Objects

- Interfaces and the implements keyword

- Constructors

- Using the Scanner class for user input

- Working with ArrayList to manage collections

## Usage Instructions

**Prerequisites**

- Java Development Kit (JDK) installed on your system.

## Running the Application

1. Clone or download the repository.

```bash
git clone https://github.com/Rithesh077/Java-record-manager
```

2. Navigate to the project directory in your terminal or command prompt.

```bash
cd Java-record-manager
```

3. Compile the Java code.

```bash
javac src/main/java/com/example/recordmanager/*.java
```

4. Run the application.

```bash
java com.example.recordmanager.RecordManager
```

5. Follow the on-screen instructions to interact with the record manager.

## Future Enhancements

- Data Persistance: Saving the records to a local file (eg: JSON or csv) so that the data isn't lost when the program closes.
  -New Record Type:Implementing the AnalyzedTextRecord class to analyze text records.
- User Authentication: Adding a login system to restrict access to the record manager.
  -Structured ID:Implement a new class for more complex and meaningful ID.
- Error Handling: Implementing try-catch blocks to handle potential errors and exceptions.
