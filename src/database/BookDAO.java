package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public Book findBook(int book_id) {
        Book book = null;

        String sql = "SELECT * FROM books WHERE books.id = ?";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, book_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"));
                break;
            }

        } catch (SQLException e) {
            System.out.println("Failed to get your book! Try again!");
            e.printStackTrace();

        }

        return book;
    }

    public List<Book> findBook(String title, String author, boolean show_unavailable) {

        List<Book> books = new ArrayList<Book>();

        String sql;
        if (title == null && author == null) {
            return null;
        } else if (author == null) {
            sql = "SELECT * FROM books WHERE books.title LIKE ? AND (books.available = true OR books.available = ?)";
        } else if (title == null) {
            sql = "SELECT * FROM books WHERE books.author LIKE ? AND (books.available = true OR books.available = ?)";
        } else {
            sql = "SELECT * FROM books WHERE books.title LIKE ? AND books.author LIKE ? AND (books.available = true OR books.available = ?)";
        }


        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            if (author == null) {
                ps.setString(1, "%" + title + "%");
                ps.setBoolean(2, !show_unavailable);
            } else if (title == null) {
                ps.setString(1, "%" + author + "%");
                ps.setBoolean(2, !show_unavailable);
            } else {
                ps.setString(1, "%" + title + "%");
                ps.setString(2, "%" + author + "%");
                ps.setBoolean(3, !show_unavailable);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available")));
            }

        } catch (SQLException e) {
            System.out.println("Failed to get your book! Try again!");
            e.printStackTrace();

        }

        return books;


    }

    public void addBook(String title, String author) {
        String sql = "INSERT INTO books(title, author, available) VALUES(?,?,?)";

        try {
            Connection conn = Database.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setBoolean(3, true);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to add book");
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();

        String sql = "select * from books";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available")));
            }

        } catch (SQLException e) {
            System.out.println("Failed to get all books");
            e.printStackTrace();

        }

        return books;

    }

    public void setAvailable(boolean available, int bookId) {
        String sql = "UPDATE books SET available = ? WHERE id = ?";

        try {
            Connection conn = Database.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, available);
            ps.setInt(2, bookId);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed updating book");
            e.printStackTrace();
        }

    }

    public void deleteBook(int bookId) {
        String sql = "DELETE FROM books WHERE id = ? AND available = true";

        try {
            Connection conn = Database.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookId);

            ps.executeUpdate();
            System.out.println("Book deleted successfully");

        } catch (SQLException e) {
            System.out.println("Failed deleting book. Maybe book is already loaned or does not exist.");
            e.printStackTrace();
        }
    }

}
