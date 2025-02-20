package input;

import java.util.Scanner;

public class InputHandler {
    public static Scanner scanner = new Scanner(System.in);

    public static AccessMode getMode() {
        while (true) {
            while (!(scanner.hasNextInt())) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter number between 0 and 2");
            }
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 0:
                    return AccessMode.EXITING;
                case 1:
                    return AccessMode.USER;
                case 2:
                    return AccessMode.ADMIN;
                default:
                    System.out.println("Invalid input. Please enter number between 0 and 2");
            }
        }
    }

    public static UserMenuMode getUserMenu() {
        while (true) {
            while (!(scanner.hasNextInt())) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter number between 0 and 4");
            }
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 0:
                    return UserMenuMode.EXITING;
                case 1:
                    return UserMenuMode.LOAN;
                case 2:
                    return UserMenuMode.RETURN;
                case 3:
                    return UserMenuMode.MY_LOANS;
                case 4:
                    return UserMenuMode.SEARCH;
                default:
                    System.out.println("Invalid input. Please enter number between 0 and 4");
            }
        }
    }

    public static AdminMenuMode getAdminMenuMode() {
        while (true) {
            while (!(scanner.hasNextInt())) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter number between 0 and 3");
            }
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 0:
                    return AdminMenuMode.EXITING;
                case 1:
                    return AdminMenuMode.ADD;
                case 2:
                    return AdminMenuMode.REMOVE;
                case 3:
                    return AdminMenuMode.ALL_BOOKS;
                default:
                    System.out.println("Invalid input. Please enter number between 0 and 3");
            }
        }
    }

    public static SearchMenuMode getSearchMenuMode() {
        while (true) {
            while (!(scanner.hasNextInt())) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter number between 0 and 2");
            }
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 0:
                    return SearchMenuMode.EXITING;
                case 1:
                    return SearchMenuMode.BY_TITLE;
                case 2:
                    return SearchMenuMode.BY_AUTHOR;

                default:
                    System.out.println("Invalid input. Please enter number between 0 and 2");
            }
        }
    }

    public static int getIntInRange(int min, int max) {
        while (true) {
            if(scanner.hasNextInt()){
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    scanner.nextLine();
                    return input;
                }
            }
            scanner.nextLine();
            System.out.println("Invalid input. Enter an Integer between " + min + " and " + max + ".");
        }
    }
    public static String getTitle() {
        while (true) {
            String answer = scanner.nextLine();
            if (!answer.isEmpty() && answer.length() <= 40 ) {
                return answer;
            }
            System.out.println("Invalid input! Maximum length 40 characters. Letters only.");
        }

    }
    public static String getAuthor() {
        while(true) {
            String answer = scanner.nextLine();
            if (!answer.isEmpty() && answer.length() <= 50 ) {
                return answer;
            }
            System.out.println("Invalid input! Maximum length  50 characters. Letters only.");
        }
    }
    public static String getUserName() {
        while(true) {
            String answer = scanner.nextLine();
            if (!answer.isEmpty() && answer.length() <= 10 ) {
                return answer;
            }
            System.out.println("Invalid input! Maximum length  10 characters. Letters only.");
        }
    }
}
