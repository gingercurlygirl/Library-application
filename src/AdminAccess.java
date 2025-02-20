import java.util.List;

public interface AdminAccess extends UserAccess {
    List<Book> getAllBooks();
    void addBook(String title, String author);
    List<Loan> getAllLoans();
    void deleteBook(int book_id);
}
