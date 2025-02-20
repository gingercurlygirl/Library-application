import input.*;

public class ConsoleView {

    private String user_name;

    private void returnMenu(UserAccess access) {
        System.out.println("##   Return a book   ##");
        boolean running = true;
        while (running) {
            System.out.println("Please enter the author and title of the book you wish to return: ");
            String author = InputHandler.getAuthor();
            String title = InputHandler.getTitle();
            Book book = access.findBook(title, author);
            if (!book.available) {
                access.returnBook(book);
                System.out.println("Return completed! Thank you!");
                running = false;
            } else {
                System.out.println("No book found, try again.");
            }
        }
    }


    private void loanMenu(UserAccess access) {
        System.out.println("##   Loan a book   ##");
        boolean running = true;
        while (running) {
            System.out.println("Please enter the author and title of the book you wish to loan: ");
            String author = InputHandler.getAuthor();
            String title = InputHandler.getTitle();
            Book book = access.findBook(title, author);
            if (book != null) {
                access.loanBook(user_name, book);
                System.out.println("Loan completed! Enjoy your book.");
                running = false;
            } else {
                System.out.println("No book found, try again.");
            }
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
                    Book book = access.findBook(InputHandler.getTitle(), null);
                    if (book != null) {
                        System.out.println("We found one book: " + book);
                        running = false;
                    } else {
                        System.out.println("No book found");
                    }
                }
                case SearchMenuMode.BY_AUTHOR -> {
                    System.out.println("##   Search by author   ##");
                    System.out.println("Please enter author of the book you wish to find: ");
                    Book book = access.findBook(null, InputHandler.getAuthor());
                    if (book != null) {
                        System.out.println("We found one book: " + book);
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
        System.out.println("Please write your user name:");
        user_name = InputHandler.getUserName();


        boolean running = true;
        while (running) {
            System.out.println("What do you want to do?\n");
            System.out.println("1. Loan a book");
            System.out.println("2. Return a book");
            System.out.println("3. List of all my loans");
            System.out.println("4. Search a book");
            System.out.println("0. Quit to main menu");
            UserMenuMode mode = InputHandler.getUserMenu();
            switch (mode) {
                case UserMenuMode.LOAN -> loanMenu(access);
                case UserMenuMode.RETURN -> returnMenu(access);
                case UserMenuMode.MY_LOANS -> {
                }
                case UserMenuMode.SEARCH -> searchMenu(access);
                case UserMenuMode.EXITING -> running = false;
            }
        }
    }


    private void workWithAdmin(AdminAccess access) {
        boolean running = true;
        while (running) {
            System.out.println("##   Logged in as administrator   ##");
            System.out.println("What do you want to do?\n");
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
                    access.deleteBook(InputHandler.getInt());
                }

                case AdminMenuMode.ALL_BOOKS -> System.out.println(access.getAllBooks());
                case AdminMenuMode.EXITING -> running = false;
            }
        }

    }

    public void showMenu() {
        boolean running = true;
        System.out.println("##   Welcome to the library!   ##");
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


