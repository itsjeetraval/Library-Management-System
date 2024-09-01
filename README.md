
# Library Management System

## Overview

Welcome to the **Library Management System**! This Java-based application helps you efficiently manage library operations. It offers functionalities for managing books and users, including adding new books and users, viewing available books, borrowing, and returning books. The system uses MySQL for database management and JDBC for database connectivity.


## Features

- **Add New Books** 📖: Add books with details like ISBN, title, author, publication year, and availability.
- **Add New Users** 👤: Register new users with their name and email.
- **View Available Books** 🔍: List all books that are currently available for borrowing.
- **Borrow Books** 📚➡️🚶: Borrow books and update their availability status.
- **Return Books** 🚶➡️📚: Return books and make them available for other users.

## Technologies Used

- **Java** ☕: Core programming language.
- **JDBC**: For database connectivity.
- **MySQL** 🗄️: Database management system.
- **JUnit** 🧪: For unit testing.

## Project Structure

- **`Book`** 📚: Entity representing a book with attributes such as ID, ISBN, title, author, publication year, and availability status.
- **`User`** 👤: Entity representing a user with attributes like ID, name, and email.
- **`BookDAO`** 🏷️: Data Access Object for book-related database operations.
- **`UserDAO`** 🏷️: Data Access Object for user-related database operations.
- **`LibraryService`** ⚙️: Contains business logic for managing books and users.
- **`LibraryManagementSystem`** 🏛️: Main class with a menu-driven interface for user interactions.
- **`DatabaseConnection`** 🔗: Manages the connection to the MySQL database.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher ☕
- MySQL database server 🗄️
- Maven or Gradle (if applicable) 📦

### Setup

1. **Clone the Repository** 🧲

   ```bash
   git clone https://github.com/yourusername/library-management-system.git
   cd library-management-system
   ```

2. **Set Up the Database** 🛠️

   - Ensure MySQL is installed and running.
   - Create a database named `library_management`.
   - Set up the `books` and `users` tables using the provided schema.

3. **Configure Database Connection** 🔧

   - Update the `DatabaseConnection.java` file with your database credentials if needed.

4. **Build and Run** 🚀

   - Compile and run the application using your preferred IDE or command line.

   ```bash
   javac -cp .;path/to/mysql-connector.jar *.java
   java -cp .;path/to/mysql-connector.jar LibraryManagementSystem
   ```

## Testing

Unit tests are included to ensure functionality. Run the tests using your preferred testing framework.

```bash
javac -cp .;path/to/junit.jar;path/to/mysql-connector.jar *.java
java -cp .;path/to/junit.jar;path/to/mysql-connector.jar org.junit.runner.JUnitCore LibraryServiceTest
```

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository 🍴.
2. Create a new branch (`git checkout -b feature/your-feature`) 🌿.
3. Commit your changes (`git commit -am 'Add new feature'`) ✏️.
4. Push to the branch (`git push origin feature/your-feature`) ⬆️.
5. Create a new Pull Request 🤝.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgements

- [MySQL](https://www.mysql.com/) 🗄️
- [JUnit](https://junit.org/junit4/) 🧪
- [JDBC](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/) 🔗

---

Feel free to adjust the emojis and text as needed!
