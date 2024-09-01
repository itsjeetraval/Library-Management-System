import java.sql.*;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryServiceTest {

    private Connection connection;
    private LibraryService libraryService;
    private BookDAO bookDAO;
    private UserDAO userDAO;

    @Before
    public void setUp() throws SQLException {
        try {
            // Initialize the connection and DAOs
            connection = DatabaseConnection.getConnection();
            bookDAO = new BookDAO();
            userDAO = new UserDAO();
            libraryService = new LibraryService(bookDAO, userDAO);

            // Clear the database tables before each test
            clearDatabase();
        } catch (SQLException e) {
            System.err.println("Error initializing database connection: " + e.getMessage());
            throw e;
        }
    }

    @After
    public void tearDown() throws SQLException {
        try {
            // Clean up the database and close the connection after each test
            clearDatabase();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    private void clearDatabase() throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM books");
            statement.executeUpdate("DELETE FROM users");
        }
    }

    @Test
    public void testAddBook() throws SQLException {
        System.out.println("addBook");
        Book book = new Book(0, "1234567890", "Test Book", "Test Author", 2024, true);

        // Add the book using LibraryService
        libraryService.addBook(book);

        // Verify the book was added
        List<Book> availableBooks = libraryService.viewAvailableBooks();
        assertTrue("The book should be in the list of available books.", availableBooks.stream()
            .anyMatch(b -> b.getIsbn().equals("1234567890") && b.getTitle().equals("Test Book") &&
                            b.getAuthor().equals("Test Author") && b.getPublicationYear() == 2024 &&
                            b.isAvailable()));
    }

    public void testAddBookDevil() throws SQLException {
        System.out.println("addBook");
        Book book = new Book(0,"1234567891", "Devil", "Test Author", 2024, true);

        // Add the book using LibraryService
        libraryService.addBook(book);

        // Verify the book was added
        List<Book> availableBooks = libraryService.viewAvailableBooks();
        assertFalse("The book should not be devil in the list of available books.", availableBooks.stream()
            .anyMatch(b -> b.getIsbn().equals("1234567891") && b.getTitle().equals("Devil") &&
                            b.getAuthor().equals("Test Author") && b.getPublicationYear() == 2024 &&
                            b.isAvailable()));
    }

    
    @Test
    public void testAddUser() throws SQLException {
        System.out.println("addUser");
        User user = new User(0, "John Doe", "john.doe@example.com");

        // Add the user using LibraryService
        libraryService.addUser(user);

        // Verify the user was added
        User retrievedUser = userDAO.getUserById(1); // Assuming ID is auto-incremented and starts at 1
        assertNotNull("User should be retrieved from the database.", retrievedUser);
        assertEquals("User's name should be 'John Doe'.", "John Doe", retrievedUser.getName());
        assertEquals("User's email should be 'john.doe@example.com'.", "john.doe@example.com", retrievedUser.getEmail());
    }

    @Test
    public void testViewAvailableBooks() throws SQLException {
        System.out.println("viewAvailableBooks");
        Book book = new Book(0, "1234567890", "Test Book", "Test Author", 2024, true);
        libraryService.addBook(book);

        List<Book> availableBooks = libraryService.viewAvailableBooks();
        assertFalse("The list of available books should not be empty.", availableBooks.isEmpty());
    }
//
//    @Test
//    public void testBorrowBook() throws SQLException {
//        System.out.println("borrowBook");
//        Book book = new Book(0, "1234567890", "Test Book", "Test Author", 2024, true);
//        libraryService.addBook(book);
//
//        User user = new User(0, "Jane Doe", "jane.doe@example.com");
//        userDAO.addUser(user);
//
//        // Borrow the book
//        libraryService.borrowBook(1, 1); // Assuming IDs are 1
//
//        // Verify the book is no longer available
//        Book borrowedBook = bookDAO.getBookById(1);
//        assertFalse("The book should no longer be available.", borrowedBook.isAvailable());
//    }

    @Test
    public void testReturnBook() throws SQLException {
        System.out.println("returnBook");
        Book book = new Book(0, "1234567890", "Test Book", "Test Author", 2024, false); // Initially borrowed
        libraryService.addBook(book);

        // Return the book
        libraryService.returnBook(1); // Assuming ID is 1

        // Verify the book is available again
        Book returnedBook = bookDAO.getBookById(1);
        assertTrue("The book should be available again.", returnedBook.isAvailable());
    }


  
}
