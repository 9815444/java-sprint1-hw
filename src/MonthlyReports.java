import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReports {

    HashMap<Integer, ArrayList<MonthlyReport>> data = new HashMap<>();
    boolean ReportsRead;

    public MonthlyReports() {
        ArrayList<MonthlyReport> reports = new ArrayList<>();
        for (int i=1;i<=3;i++){
            this.data.put(i, reports);
        }
        this.ReportsRead = false;
    }

    public void read() {

        String directory = "Reports/";
        HashMap<Integer, ArrayList<MonthlyReport>> dataMonths = new HashMap<>();
        Integer noYear = 2021;

        for (int i = 1; i<=3; i++) {

            ArrayList<MonthlyReport> dataOneMonth = new ArrayList<>();

            String fileName = directory + "m.20210" + i + ".csv";

            String Text = Parsing.readFileContentsOrNull(fileName);

            if (Text == null) {
                continue;
            }

            String[] lines = Text.split("\n");

            boolean isOneString = true;
            for (String line : lines) {
                if (isOneString) {
                    isOneString = false;
                } else {
                    String[] lineContents = line.split(",");
                    String item_nameValue = lineContents[0];
                    Boolean is_expenseValue = Boolean.parseBoolean(lineContents[1]);
                    Double quantityValue = Double.parseDouble(lineContents[2]);
                    Double sum_of_oneValue = Double.parseDouble(lineContents[3]);

                    MonthlyReport dataMonth = new MonthlyReport(noYear, i, item_nameValue, is_expenseValue, quantityValue, sum_of_oneValue);
                    dataOneMonth.add(dataMonth);
                }
            }
            this.data.put(i, dataOneMonth);
        }
        this.ReportsRead = true;
        System.out.println("Cчитанны месячные отчеты!");
    }

    public static void print(MonthlyReports monthlyReports){

        if (!monthlyReports.ReportsRead) {
            System.out.println("Не считанны месячные отчеты!");
            return;
        }

        boolean mistake = false;

        for (int i = 1; i<=3; i++) {
            Double maxSumExpense = 0.0;
            String maxSumExpenseItem = "";
            Double maxSumIncome = 0.0;
            String maxSumIncomeItem = "";

            ArrayList<MonthlyReport> dataOneMonth = monthlyReports.data.get(i);
            for (MonthlyReport record : dataOneMonth) {
                if ((record.is_expense) && (maxSumExpense < record.sum)) {
                    maxSumExpense = record.sum;
                    maxSumExpenseItem = record.item_name;
                }
                if ((!record.is_expense) && (maxSumIncome < record.sum)) {
                    maxSumIncome = record.sum;
                    maxSumIncomeItem = record.item_name;
                }
            }

            System.out.println(i + " месяц 2021 года:");
            System.out.println("Самый прибыльный товар:");
            System.out.println("Товар: " + maxSumIncomeItem);
            System.out.println("Сумма: " + maxSumIncome);

            System.out.println("Самая большая трата:");
            System.out.println("Товар: " + maxSumExpenseItem);
            System.out.println("Сумма: " + maxSumExpense);

        }

    }

}
