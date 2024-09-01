import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {

    private static LibraryService libraryService;

    public static void main(String[] args) {
        // Initialize the DAO and Service classes
        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();
        libraryService = new LibraryService(bookDAO, userDAO);

        // Display the menu to the user
        displayMenu();
    }

    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add a new book");
            System.out.println("2. Add a new user");
            System.out.println("3. View available books");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    addUser(scanner);
                    break;
                case 3:
                    viewAvailableBooks();
                    break;
                case 4:
                    borrowBook(scanner);
                    break;
                case 5:
                    returnBook(scanner);
                    break;
                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addBook(Scanner scanner) {
        try {
            System.out.print("Enter ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Enter Title: ");
            String title = scanner.nextLine();
            System.out.print("Enter Author: ");
            String author = scanner.nextLine();
            System.out.print("Enter Publication Year: ");
            int publicationYear = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Book book = new Book(0, isbn, title, author, publicationYear, true);
            libraryService.addBook(book);
            System.out.println("Book added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding the book: " + e.getMessage());
        }
    }

    private static void addUser(Scanner scanner) {
        try {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            User user = new User(0, name, email);
            libraryService.addUser(user);
            System.out.println("User added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding the user: " + e.getMessage());
        }
    }

    private static void viewAvailableBooks() {
        try {
            List<Book> books = libraryService.viewAvailableBooks();
            System.out.println("\nAvailable Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving available books: " + e.getMessage());
        }
    }

    private static void borrowBook(Scanner scanner) {
        try {
            System.out.print("Enter Book ID to borrow: ");
            int bookId = scanner.nextInt();
            System.out.print("Enter User ID: ");
            int userId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            libraryService.borrowBook(bookId, userId);
            System.out.println("Book borrowed successfully!");

        } catch (SQLException e) {
            System.out.println("Error borrowing the book: " + e.getMessage());
        }
    }

    private static void returnBook(Scanner scanner) {
        try {
            System.out.print("Enter Book ID to return: ");
            int bookId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            libraryService.returnBook(bookId);
            System.out.println("Book returned successfully!");

        } catch (SQLException e) {
            System.out.println("Error returning the book: " + e.getMessage());
        }
    }
}
