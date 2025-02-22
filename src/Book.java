import java.util.List;

public class Book {
    int id;
    String title;
    String author;
    boolean available;

    public Book(int id, String title, String author, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    @Override
    public String toString() {

        String availableStr;
        if (available) {
            availableStr = "available";
        }else {
            availableStr = "not available";
        }

        return "Id: " + id +
                "\tTitle: " + title  +
                "\tAuthor: " + author  +
                "\tAvailable: " + availableStr;
    }

     public static String toString(List<Book> books) {
        StringBuilder book_string = new StringBuilder();
        if (!books.isEmpty()) {
            for (Book book : books) {
                book_string.append(book.toString()).append("\n");
            }
        } else {
            book_string.append("No books found");
        }

        return book_string.toString();
    }
}
