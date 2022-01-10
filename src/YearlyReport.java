public class YearlyReport {
    int year;
    int month;
    double amount;
    boolean is_expense;


    YearlyReport(int yearValue, int monthValue, Double amountValue, Boolean is_expenseValue){
        year = yearValue;
        month = monthValue;
        amount = amountValue;
        is_expense = is_expenseValue;
    }
}
