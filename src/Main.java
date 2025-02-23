import database.Access;
import input.*;


public class Main {
    public static void main(String[] args) {

        ConsoleView view = new ConsoleView();

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
                case AccessMode.USER -> view.workWithUser(access);
                case AccessMode.ADMIN -> view.workWithAdmin(access);
                case AccessMode.EXITING -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
            }
        }
    }
}