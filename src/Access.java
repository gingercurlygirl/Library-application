import java.util.List;

public class Access implements AdminAccess, UserAccess {
    // samo ovdje smiju biti DAO objekti
    private BookDAO bookDAO = new BookDAO();

    @Override
    public void addBook(String title, String author) {
        bookDAO.addBook(title, author);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public void returnBook(Book book) {
        //bookDAO.obrisiLoan(book);
        //bookDAO.toggleAvailable(book);
    }
}
