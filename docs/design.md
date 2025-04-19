# Design Documentation

## UML Class Diagram
Here is the UML class diagram that shows the structure of the system.

![UML Diagram](uml_class_diagram.png)

## Design Patterns Used
1. **Factory Pattern**: The `LibrarySystem` uses a factory-like pattern to create and manage users and books.
2. **Observer Pattern**: The lending system follows an observer pattern, where the due dates are observed by users.

## Class Descriptions
- **LibrarySystem**: Manages books, users, and lending.
- **Book**: Represents a book with title and author(s).
- **User**: Represents a user (either student or faculty).
