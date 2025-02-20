import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Access implements AdminAccess, UserAccess {

    private BookDAO bookDAO = new BookDAO();
    private LoanDAO loanDAO = new LoanDAO();

    @Override
    public void addBook(String title, String author) {
        bookDAO.addBook(title, author);
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanDAO.getAllLoans();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public void returnBook(Book book) {
        bookDAO.setAvailable(true, book.id);
        loanDAO.deleteLoan(book.id);

    }

    @Override
    public void loanBook(String user_name, Book book) {
        LocalDate loan_date = LocalDate.now();
        LocalDate return_date = loan_date.plusMonths(1);

        loanDAO.loanBook(user_name, book.id, Date.valueOf(loan_date), Date.valueOf(return_date));

    }

    @Override
    public Book findBook(String title, String author) {
        return bookDAO.findBook(title, author);
    }
}
