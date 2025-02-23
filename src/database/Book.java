package database;

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
        } else {
            availableStr = "not available";
        }

        return "Id: " + String.format("%-3s", id) +
                "\tTitle: " + String.format("%-50s", title) +
                "\tAuthor: " + String.format("%-30s", author) +
                "\tAvailable: " + availableStr;
    }

    public String toString(int number) {

        String availableStr;
        if (available) {
            availableStr = "available";
        } else {
            availableStr = "not available";
        }

        return "Number: " + String.format("%-3s", number) +
                "\tTitle: " + String.format("%-50s", title) +
                "\tAuthor: " + String.format("%-30s", author) +
                "\tAvailable: " + availableStr;
    }

    public String toStringUser() {
        return "Title: " + String.format("%-50s", title) +
                "\tAuthor: " + String.format("%-30s", author);
    }

    public static String toString(List<Book> books) {
        StringBuilder book_string = new StringBuilder();

        for (Book book : books) {
            book_string.append(book.toString()).append("\n");
        }

        return book_string.toString();
    }

    public static String toString(List<Book> books, boolean show_unavailable) {
        StringBuilder book_string = new StringBuilder();

        if (!books.isEmpty()) {
            for (int i = 0; i < books.size(); i++) {
                // Always show books that are available but only show unavailable if show_unavailable is true
                if (show_unavailable || books.get(i).available) {
                    book_string.append(books.get(i).toString(i + 1)).append("\n");
                }
            }
        } else {
            book_string.append("No books found");
        }

        return book_string.toString();
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }
}
