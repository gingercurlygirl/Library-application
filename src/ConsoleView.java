import input.*;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private String user_name;

    private void returnMenu(UserAccess access) {
        System.out.println("##   Return a book   ##");
        System.out.println("Please enter the author of the book you wish to return: ");
        String author = InputHandler.getAuthor();
        System.out.println("Please enter the title of the book you wish to return: ");
        String title = InputHandler.getTitle();
        Loan loan = access.findLoan(user_name, title, author);
        if (loan != null) {
            access.returnBook(loan);
            System.out.println("Return completed! Thank you!");
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
        List<Book> books = access.findBook(title, author);
        if (books.size() == 1 && books.getFirst().available) {
            access.loanBook(user_name, books.getFirst());
            System.out.println("Loan completed! You loaned \n" + books.getFirst() + "\nEnjoy your book.");
        } else if (books.size() > 1) {
            System.out.println("We found more then one book, please query more precisely:\n" + Book.toString(books));
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
                    List<Book> books = access.findBook(InputHandler.getTitle(), null);
                    if (!books.isEmpty()) {
                        System.out.println("We found: \n" + Book.toString(books));
                        running = false;
                    } else {
                        System.out.println("No book found");
                    }
                }
                case SearchMenuMode.BY_AUTHOR -> {
                    System.out.println("##   Search by author   ##");
                    System.out.println("Please enter author of the book you wish to find: ");
                    List<Book> books = access.findBook(null, InputHandler.getAuthor());
                    if (!books.isEmpty()) {
                        System.out.println("We found: \n" + Book.toString(books));
                        running = false;
                    } else {
                        System.out.println("No book found");
                    }
                }
                case SearchMenuMode.EXITING -> running = false;
            }
        }
    }

    private void workWithUser(UserAccess access) {
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


    private void workWithAdmin(AdminAccess access) {
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

    public void showMenu() {
        boolean running = true;
        System.out.println("\n##   Welcome to the library!   ##\n");
        while (running) {
            System.out.println("---Menu---");
            System.out.println("1. User");
            System.out.println("2. Admin");
            System.out.println("0. Exit");

            Access access = new Access();
            AccessMode mode = InputHandler.getMode();
            switch (mode) {
                case AccessMode.USER -> workWithUser(access);
                case AccessMode.ADMIN -> workWithAdmin(access);
                case AccessMode.EXITING -> {
                    System.out.println("Goodbye!");
                    running = false;
                }

            }
        }
    }

}


