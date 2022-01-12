import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReports {
    ArrayList<YearlyReport> data = new ArrayList<>();
    boolean ReportsRead = false;

    public void read() {

        String directory = "Reports/";
        Integer noYear = 2021;

        String fileName = directory + "y." + noYear + ".csv";
        String Text = Parsing.readFileContentsOrNull(fileName);
        String[] lines = Text.split("\r\n");

        boolean isOneString = true;
        for (String line : lines) {
            if (isOneString) {
                isOneString = false;
            } else {
                String[] lineContents = line.split(",");
                int yearValue = noYear;
                int monthValue = Integer.parseInt(lineContents[0]);
                Double amountValue = Double.parseDouble(lineContents[1]);
                Boolean is_expenseValue = Boolean.parseBoolean(lineContents[2]);

                YearlyReport dataYear = new YearlyReport(yearValue, monthValue, amountValue,  is_expenseValue);
                this.data.add(dataYear);
            }
        }
        this.ReportsRead = true;
        System.out.println("Cчитанн годовой отчет!");

    }

    public static void compareReports(MonthlyReports monthlyReports, YearlyReports yearlyReports){

        boolean reportRead = true;
        boolean mistake = false;

        if (!monthlyReports.ReportsRead) {
            System.out.println("Не считанны месячные отчеты!");
            reportRead = false;
        }
        if (!yearlyReports.ReportsRead) {
            System.out.println("Не считанн годовой отчет!");
            reportRead = false;
        }

        if (!reportRead) {
            return;
        }

        for (int i = 1; i<=3; i++) {
            Double sumExpense = 0.0;
            Double sumIncome = 0.0;

            ArrayList<MonthlyReport> dataOneMonth = monthlyReports.data.get(i);
            for (MonthlyReport record : dataOneMonth) {
                if (record.is_expense) {
                    sumExpense += record.sum;
                }
                else {
                    sumIncome += record.sum;
                }
            }

            for (YearlyReport recordYear : yearlyReports.data) {
                if (recordYear.month == i) {
                    if ((!recordYear.is_expense) && (recordYear.amount !=  sumIncome)) {
                        System.out.println("Не совпадает значение дохода за " + i + " месяц 2021 г.");
                        mistake = true;
                    }
                    if ((recordYear.is_expense) && (recordYear.amount !=  sumExpense)) {
                        System.out.println("Не совпадает значение расхода за " + i + " месяц 2021 г.");
                        mistake = true;
                    }
                }
            }
        }

        if (!mistake) {
            System.out.println("Все значения совпадают!");
        }

    }

    public static void print(YearlyReports yearlyReports){

        HashMap<Integer, Double> profit = new HashMap<>();

        Double sumExpenses = 0.0;
        Integer qExpenses = 0;

        Double sumIncome = 0.0;
        Integer qIncome = 0;

        for (int i = 1; i<=3; i++) {
            profit.put(i, 0.0);
        }

        for (YearlyReport recordYear : yearlyReports.data) {

            Double Sum = profit.get(recordYear.month);

            if (recordYear.is_expense) {
                Sum -= recordYear.amount;
                sumExpenses += recordYear.amount;
                qExpenses ++;
            }
            else {
                Sum += recordYear.amount;
                sumIncome += recordYear.amount;
                qIncome ++;
            }
            profit.put(recordYear.month, Sum);

        }

        System.out.println("2021 год. Прибыль:");
        for (Integer mouth : profit.keySet()) {
            System.out.println(mouth + " месяц - " + profit.get(mouth));
        }

        System.out.println("Средний расход за месяц - " + (qExpenses == 0 ? 0 : sumExpenses / qExpenses));
        System.out.println("Средний доход за месяц - " + (qIncome == 0 ? 0 : sumIncome / qIncome));

    }

}
