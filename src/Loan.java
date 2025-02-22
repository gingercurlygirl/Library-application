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
        return  "\nName= " + user_name +
                "\nBook id= " + book_id +
                "\nLoan date= " + loan_date  +
                "\nReturn date= " + return_date + "\n" ;
    }
}
