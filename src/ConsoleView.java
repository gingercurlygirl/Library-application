import database.UserAccess;
import database.AdminAccess;
import database.Loan;
import database.Book;
import input.*;

import java.util.List;

public class ConsoleView {

    private String user_name;

    private void returnMenu(UserAccess access) {
        System.out.println("##   Return a book   ##");
        System.out.println("Please enter the author of the book you wish to return: ");
        String author = InputHandler.getAuthor();
        System.out.println("Please enter the title of the book you wish to return: ");
        String title = InputHandler.getTitle();
        List<Loan> loans = access.findLoan(user_name, title, author);
        if (loans.size() == 1) {
            access.returnBook(loans.getFirst());
            System.out.println("Loan return completed! You returned: \n" + loans.getFirst() + "\nThank you.");
        } else if (loans.size() > 1) {
            System.out.println("We found more then one loan, please use number of specific loan:\n" + Loan.toString(loans));
            int loan_index = InputHandler.getIntInRange(1, loans.size() + 1) - 1;
            access.returnBook(loans.get(loan_index));
            System.out.println("Loan return completed! You returned: \n" + loans.get(loan_index) + "\nThank you.");

        } else {
            System.out.println("No loan found.");
        }
    }


    private void loanMenu(UserAccess access) {
        System.out.println("##   Loan a book   ##");
        System.out.println("Please enter the author of the book you wish to loan: ");
        String author = InputHandler.getAuthor();
        System.out.println("Please enter the title of the book you wish to loan: ");
        String title = InputHandler.getTitle();
        List<Book> books = access.findBook(title, author, false);
        if (books.size() == 1 && books.getFirst().isAvailable()) {
            access.loanBook(user_name, books.getFirst());
            System.out.println("Loan completed! You loaned: \n" + books.getFirst() + "\nEnjoy your book.");
        } else if (books.size() > 1) {
            System.out.println("We found more then one book, please use number of specific book:\n" + Book.toString(books, false));
            int book_index = InputHandler.getIntInRange(1, books.size() + 1) - 1;
            access.loanBook(user_name, books.get(book_index));
            System.out.println("Loan completed! You loaned: \n" + books.get(book_index) + "\nEnjoy your book.");
        } else if (books.isEmpty()) {
            System.out.println("No loan found.");
        } else {
            System.out.println("Book is not available.");
        }

    }

    private void searchMenu(UserAccess access) {
        boolean running = true;
        while (running) {
            System.out.println("##   Search   ##");
            System.out.println("Please select search method: \n");
            System.out.println("1. By title");
            System.out.println("2. By author");
            System.out.println("0. Go back");
            SearchMenuMode mode = InputHandler.getSearchMenuMode();
            switch (mode) {
                case SearchMenuMode.BY_TITLE -> {
                    System.out.println("##   Search by title   ##");
                    System.out.println("Please enter the title of the book you wish to find: ");
                    List<Book> books = access.findBook(InputHandler.getTitle(), null, true);
                    if (!books.isEmpty()) {
                        System.out.println("We found: \n" + Book.toString(books, true));
                        running = false;
                    } else {
                        System.out.println("No book found");
                    }
                }
                case SearchMenuMode.BY_AUTHOR -> {
                    System.out.println("##   Search by author   ##");
                    System.out.println("Please enter author of the book you wish to find: ");
                    List<Book> books = access.findBook(null, InputHandler.getAuthor(), true);
                    if (!books.isEmpty()) {
                        System.out.println("We found: \n" + Book.toString(books, true));
                        running = false;
                    } else {
                        System.out.println("No book found");
                    }
                }
                case SearchMenuMode.EXITING -> running = false;
            }
        }
    }

    public void workWithUser(UserAccess access) {
        System.out.println("\nPlease write your user name:");
        user_name = InputHandler.getUserName();


        boolean running = true;
        System.out.println("\nHi " + user_name + ", What do you want to do?\n");
        while (running) {
            System.out.println("\n1. Loan a book");
            System.out.println("2. Return a book");
            System.out.println("3. List of all my loans");
            System.out.println("4. Search a book");
            System.out.println("0. Quit to main menu");
            UserMenuMode mode = InputHandler.getUserMenu();
            switch (mode) {
                case UserMenuMode.LOAN -> loanMenu(access);
                case UserMenuMode.RETURN -> returnMenu(access);
                case UserMenuMode.MY_LOANS -> System.out.println(Loan.toString(access.getAllLoans(user_name)));
                case UserMenuMode.SEARCH -> searchMenu(access);
                case UserMenuMode.EXITING -> running = false;
            }
        }
    }


    public void workWithAdmin(AdminAccess access) {
        boolean running = true;
        while (running) {
            System.out.println("\n##   Logged in as administrator   ##");
            System.out.println("\nWhat do you want to do?");
            System.out.println("1. Add book");
            System.out.println("2. Remove book");
            System.out.println("3. List all books and availabilities");
            System.out.println("0. Quit to main menu");
            AdminMenuMode mode = InputHandler.getAdminMenuMode();
            switch (mode) {
                case AdminMenuMode.ADD -> {
                    System.out.println("Enter title:");
                    String title = InputHandler.getTitle();
                    System.out.println("Enter author:");
                    String author = InputHandler.getAuthor();
                    access.addBook(title, author);
                }
                case AdminMenuMode.REMOVE -> {
                    System.out.println("Enter book id: ");
                    int book_id = InputHandler.getInt();
                    Book book = access.findBook(book_id);
                    if (book == null) {
                        System.out.println("Book not found");
                    } else {
                        access.deleteBook(book_id);
                    }
                }

                case AdminMenuMode.ALL_BOOKS -> System.out.println(Book.toString(access.getAllBooks()));
                case AdminMenuMode.EXITING -> running = false;
            }
        }

    }
}
