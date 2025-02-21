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
    public List<Loan> getAllLoans(String user_name) {
        return loanDAO.getAllLoans(user_name);
    }

    @Override
    public void deleteBook(int book_id) {
        bookDAO.deleteBook(book_id);
    }

    @Override
    public Book findBook(int book_id) {
        return bookDAO.findBook(book_id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public void returnBook(Book book) {
        if (book == null || book.available) {
            return;
        }

        // Ensure that book exists in books, that found book is same as given book, and that found book is not available
        Book book_by_finding = bookDAO.findBook(book.id);
        if (book_by_finding == null || book.id != book_by_finding.id || book_by_finding.available) {
            return;
        }

        // Ensure that loan exists in loans, that found loan contains the same book as given book
        Loan loan_by_finding = loanDAO.findLoan(book.id);
        if (loan_by_finding == null || book.id != loan_by_finding.book_id) {
            return;
        }

        // TODO:
        //if (username != loan_by_finding.user_name) {
        //    return;
        //}

        loanDAO.deleteLoan(book.id);
        bookDAO.setAvailable(true, book.id);
    }

    @Override
    public void loanBook(String user_name, Book book) {
        if (book == null || !book.available) {
            return;
        }

        LocalDate loan_date = LocalDate.now();
        LocalDate return_date = loan_date.plusMonths(1);

        loanDAO.loanBook(user_name, book.id, Date.valueOf(loan_date), Date.valueOf(return_date));
        bookDAO.setAvailable(false, book.id);
    }

    @Override
    public Book findBook(String title, String author) {
        return bookDAO.findBook(title, author);
    }
}
