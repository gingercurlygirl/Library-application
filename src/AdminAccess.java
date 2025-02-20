import java.util.List;

public interface AdminAccess extends UserAccess {

    public void addBook(String title, String author);
    public List<Loan> getAllLoans();

}
