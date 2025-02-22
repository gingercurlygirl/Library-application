import java.util.List;

public interface UserAccess {
    void returnBook(Loan loan);
    void loanBook(String user_name, Book book);
    Book findBook(String title, String author);
    List<Loan> getAllLoans(String user_name);
    Loan findLoan(String user_name, String title, String author);
}
