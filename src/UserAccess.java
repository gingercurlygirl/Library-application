import java.util.List;

public interface UserAccess {
    public List<Book> getAllBooks();
    public void returnBook(Book book);
    public void loanBook(String user_name, String title);
}
