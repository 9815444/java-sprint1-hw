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

        MonthlyReports monthlyReports = new MonthlyReports();
        YearlyReports yearlyReports = new YearlyReports();

        printMenu();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                monthlyReports.read();
            } else if (userInput == 2) {
                yearlyReports.read();
            } else if (userInput == 3) {
                YearlyReports.compareReports(monthlyReports, yearlyReports);
            } else if (userInput == 4) {
                MonthlyReports.print(monthlyReports);
            } else if (userInput == 5) {
                YearlyReports.print(yearlyReports);
            } else if (userInput == 0) {
                break;
            } else
                System.out.println("Неверный ввод!");
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

