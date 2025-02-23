package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class LoanDAO {

        public List<Loan> findLoan(String user_name, String title, String author) {
        List<Loan> loans = new ArrayList<>();

        String sql = "SELECT * FROM loans INNER JOIN books ON books.id = loans.book_id WHERE user_name = ? AND title LIKE ? AND author LIKE ?";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user_name);
            ps.setString(2, "%" + title + "%");
            ps.setString(3, "%" + author + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book(rs.getInt("book_id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"));
                loans.add(new Loan(rs.getInt("id"), rs.getString("user_name"), rs.getInt("book_id"), rs.getString("loan_date"), rs.getString("return_date"), book));
            }

        } catch (SQLException e) {
            System.out.println("Failed to get your book! Try again!");
            e.printStackTrace();

        }

        return loans;
    }

    public Loan findLoan(int book_id) {
        Loan loan = null;

        String sql = "SELECT * FROM loans WHERE loans.book_id = ?";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, book_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                loan = new Loan(rs.getInt("id"), rs.getString("user_name"), rs.getInt("book_id"), rs.getString("loan_date"), rs.getString("return_date"), null);
                break;
            }

        } catch (SQLException e) {
            System.out.println("Failed to get your book! Try again!");
            e.printStackTrace();

        }

        return loan;
    }

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

        } catch (SQLException e) {
            System.out.println("Failed to loan book");
            e.printStackTrace();
        }
    }

    public List<Loan> getAllLoans(String user_name) {
        List<Loan> loans = new ArrayList<Loan>();

        String sql = "SELECT loans.id, loans.user_name, loans.book_id, loans.loan_date, loans.return_date, books.title, books.author FROM books INNER JOIN loans ON books.id = loans.book_id WHERE user_name = ?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user_name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                loans.add(
                        new Loan(rs.getInt("id"),
                                rs.getString("user_name"),
                                rs.getInt("book_id"),
                                rs.getString("loan_date"),
                                rs.getString("return_date"),
                                new Book(rs.getInt("book_id"),
                                        rs.getString("title"),
                                        rs.getString("author"),
                                        false
                                )
                        ));

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
        } catch (SQLException e) {
            System.out.println("Failed to return book");
            e.printStackTrace();
        }

    }


}
