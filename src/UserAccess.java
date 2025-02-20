import java.util.List;

public interface UserAccess {
    void returnBook(Book book);
    void loanBook(String user_name, Book book);
    Book findBook(String title, String author);
    List<Loan> getAllLoans(String user_name);
}
