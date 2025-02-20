import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private Scanner scanner = new Scanner(System.in);

    private void workWithUser(UserAccess access) {
        System.out.println(access.getAllBooks());


    }
    private void workWithAdmin(AdminAccess access) {
        System.out.println("Enter title: ");
        String title = scanner.next();
        System.out.println("Enter author: ");
        String author = scanner.next();

        access.addBook(title, author);
        access.loanBook("asndj", "lotr");
        System.out.println(access.getAllLoans());
    }

    public void showMenu() {

        while (true) {
            System.out.println("---Menu---");
            System.out.println("1. User");
            System.out.println("2. Admin");
            System.out.println("0. Exit");

            String choice = scanner.next();

            Access access = new Access();

            switch (choice) {
                case "1": workWithUser(access);
                    break;
                case "2": workWithAdmin(access);
                    break;
                case "0":
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

}


