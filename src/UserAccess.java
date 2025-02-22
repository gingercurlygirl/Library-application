import java.util.List;

public interface UserAccess {
    void returnBook(Loan loan);
    void loanBook(String user_name, Book book);
    List<Book> findBook(String title, String author);
    List<Loan> getAllLoans(String user_name);
    List<Loan> findLoan(String user_name, String title, String author);
}
