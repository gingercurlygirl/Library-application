import java.util.List;

public interface AdminAccess extends UserAccess {
    List<Book> getAllBooks();
    void addBook(String title, String author);
    void deleteBook(int book_id);
}
