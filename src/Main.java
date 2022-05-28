import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MonthlyReportsManager monthlyReportsManager = new MonthlyReportsManager();
        YearlyReport yearlyReport = new YearlyReport(2021);
        DataChecking dataChecking = new DataChecking();

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                monthlyReportsManager.load();
            } else if (command == 2) {
                yearlyReport.load();
            } else if (command == 3) {
                dataChecking.checkReports(monthlyReportsManager, yearlyReport);
            } else if (command == 4) {
                monthlyReportsManager.getInfo();
            } else if (command == 5) {
                yearlyReport.getInfo();
            } else if (command == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Такой команды нет. Выберите команду из списка");
            }
        }
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }

}

