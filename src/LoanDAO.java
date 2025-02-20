import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    public void loanBook(String user_name, int book_id, java.sql.Date loan_date, java.sql.Date return_date) {
        String sql = "INSERT INTO loans (user_name, book_id, loan_date, return_date) VALUES (?,?,?,?)";

        try {
            Connection conn = Database.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user_name);
            ps.setInt(2, book_id);
            ps.setDate(3, loan_date);
            ps.setDate(4, return_date);

            ps.executeUpdate();
            System.out.println("Book loaned successfully");

        } catch (SQLException e) {
            System.out.println("Failed to loan book");
            e.printStackTrace();
        }
    }

    public List<Loan> getAllLoans(String user_name) {
        List<Loan> loans = new ArrayList<Loan>();

        String sql = "SELECT * FROM loans WHERE user_name = ?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user_name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                loans.add(new Loan(rs.getInt("id"), rs.getString("user_name"), rs.getInt("book_id"), rs.getString("loan_date"), rs.getString("return_date")));
            }

        } catch (SQLException e) {
            System.out.println("failed to get all loans");
            e.printStackTrace();

        }

        return loans;

    }

    public void deleteLoan(int book_id) {
        String sql = "DELETE FROM loans WHERE book_id = ?";

        try {
            Connection conn = Database.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, book_id);

            ps.executeUpdate();
            System.out.println("Book returned successfully");

        } catch (SQLException e) {
            System.out.println("Failed to return book");
            e.printStackTrace();
        }

    }



}
