public class MonthlyReport {
    int year;
    int month;
    String item_name;
    boolean is_expense;
    double quantity;
    double sum_of_one;
    double sum;

    MonthlyReport(int yearValue, int monthValue, String item_nameValue, Boolean is_expenseValue, Double quantityValue, Double sum_of_oneValue){
        year = yearValue;
        month = monthValue;
        item_name = item_nameValue;
        is_expense = is_expenseValue;
        quantity = quantityValue;
        sum_of_one = sum_of_oneValue;
        sum = quantityValue * sum_of_oneValue;
    }



}
