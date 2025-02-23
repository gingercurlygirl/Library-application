package database;

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
    public List<Loan> findLoan(String user_name, String title, String author) {
        return loanDAO.findLoan(user_name, title, author);
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
    public void returnBook(Loan loan) {
        if (loan == null) {
            return;
        }

        loanDAO.deleteLoan(loan.getBook_id());
        bookDAO.setAvailable(true, loan.getBook_id());
    }

    @Override
    public void loanBook(String user_name, Book book) {
        if (book == null || !book.isAvailable()) {
            return;
        }

        LocalDate loan_date = LocalDate.now();
        LocalDate return_date = loan_date.plusMonths(1);

        loanDAO.loanBook(user_name, book.getId(), Date.valueOf(loan_date), Date.valueOf(return_date));
        bookDAO.setAvailable(false, book.getId());
    }

    @Override
    public List<Book> findBook(String title, String author, boolean show_unavailable) {
        return bookDAO.findBook(title, author, show_unavailable);
    }
}
