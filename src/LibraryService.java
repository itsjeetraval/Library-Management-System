
import java.sql.SQLException;
import java.util.List;

public class LibraryService {
    private BookDAO bookDAO;
    private UserDAO userDAO;

    public LibraryService(BookDAO bookDAO, UserDAO userDAO) {
        this.bookDAO = bookDAO;
        this.userDAO = userDAO;
    }

    public void addBook(Book book) throws SQLException {
        bookDAO.addBook(book);
    }

    public void addUser(User user) throws SQLException {
        userDAO.addUser(user);
    }

    public List<Book> viewAvailableBooks() throws SQLException {
        return bookDAO.getAllAvailableBooks();
    }

    public void borrowBook(int bookId, int userId) throws SQLException {
        Book book = bookDAO.getBookById(bookId);
        if (book != null && book.isAvailable()) {
            bookDAO.updateBookAvailability(bookId, false);
            System.out.println("Book borrowed successfully by user ID: " + userId);
        } else {
            throw new SQLException("Book is not available for borrowing");
        }
    }

    public void returnBook(int bookId) throws SQLException {
        bookDAO.updateBookAvailability(bookId, true);
        System.out.println("Book returned successfully!");
    }
}

