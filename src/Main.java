import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        // Поехали!

        boolean reportsRead;
        ArrayList<MonthlyReport> dataMonths = new ArrayList<>();
        dataMonths = readMonthsReports();

        ArrayList<YearlyReport> dataYears = new ArrayList<>();
        dataYears = readYearlyReports();

//        MonthlyReport a = new MonthlyReport();
//        a.readReports();
        //MonthlyReport f;
        //monthlyReport.readReports();
        /*printMenu();

        while (true){
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();
            if (userInput == 1) {

            }
            else if (userInput == 2) {

            }
            else if (userInput == 3){

            }
            else if (userInput == 4) {

            }
            else if (userInput == 5) {

            }
            else if (userInput == 0) {
                break;
            }
            else
                System.out.println("Неверный ввод!");

        }*/


    }

    public static void printMonthData(ArrayList<MonthlyReport> dataMonths){

    }

    public static ArrayList<YearlyReport> readYearlyReports() {

        String directory = "C:/Users/bb/dev/java-sprint1-hw/Reports/";
        ArrayList<YearlyReport> dataYears = new ArrayList<>();
        Integer noYear = 2021;

        String fileName = directory + "y." + noYear + ".csv";
        String Text = readFileContentsOrNull(fileName);
        String[] lines = Text.split("\n");

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
                dataYears.add(dataYear);
            }
        }

        return dataYears;
    }

    public static ArrayList<MonthlyReport> readMonthsReports() {

        String directory = "C:/Users/bb/dev/java-sprint1-hw/Reports/";
        ArrayList<MonthlyReport> dataMonths = new ArrayList<>();
        Integer noMonth = 1;
        Integer noYear = 2021;

        for (int i = 1; i<=3; i++) {

            String fileName = directory + "m.20210" + i + ".csv";

            String Text = readFileContentsOrNull(fileName);

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
                    int yearValue = noYear;
                    int monthValue = noMonth;
                    String item_nameValue = lineContents[0];
                    Boolean is_expenseValue = Boolean.parseBoolean(lineContents[1]);
                    Double quantityValue = Double.parseDouble(lineContents[2]);
                    Double sum_of_oneValue = Double.parseDouble(lineContents[3]);

                    MonthlyReport dataMonth = new MonthlyReport(yearValue, monthValue, item_nameValue, is_expenseValue, quantityValue, sum_of_oneValue);
                    dataMonths.add(dataMonth);
                }
            }
        }
        return dataMonths;
    }

    private static String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    static void printMenu() {
        System.out.println("Главное меню:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}

