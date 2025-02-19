import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public void addBook(String title, String author) {
        String sql = "INSERT INTO books(title, author, available) VALUES(?,?,?)";

        try{
            Connection conn = Database.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setBoolean(3, true);

            ps.executeUpdate();
            System.out.println("Customer added successfully");

        }catch(SQLException e){
            System.out.println("Failed to add book");
            e.printStackTrace();
        }
    }
    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<Book>();

        String sql = "select * from books";

        try{
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available")));
            }

        }catch (SQLException e){
            System.out.println("failed to get all books");
            e.printStackTrace();

        }

        return books;

}


}
