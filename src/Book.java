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

        return "\nId= " + id +
                "\nTitle= " + title  +
                "\nAuthor= " + author  +
                "\nAvailable= " + availableStr + "\n";
    }
}
