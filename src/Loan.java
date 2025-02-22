import java.util.List;

public class Loan {
    int id;
    String user_name;
    int book_id;
    String loan_date;
    String return_date;

    public Loan(int id, String user_name, int book_id, String loan_date, String return_date) {
        this.id = id;
        this.user_name = user_name;
        this.book_id = book_id;
        this.loan_date = loan_date;
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return "Name: " + user_name +
                "\tBook id: " + book_id +
                "\tLoan date: " + loan_date +
                "\tReturn date: " + return_date;
    }

    public static String toString(List<Loan> loans) {
        StringBuilder loan_string = new StringBuilder();
        if (!loans.isEmpty()) {
            for (Loan loan : loans) {
                loan_string.append(loan.toString()).append("\n");
            }
        } else {
            loan_string.append("No loans found");
        }

        return loan_string.toString();
    }
}
