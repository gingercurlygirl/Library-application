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
        return "Loan{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", book_id=" + book_id +
                ", loan_date='" + loan_date + '\'' +
                ", return_date='" + return_date + '\'' +
                '}';
    }
}
