import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        final MonthlyReportsManager monthlyReportsManager = new MonthlyReportsManager();
        final YearlyReport yearlyReport = new YearlyReport(2021);
        DataChecking dataChecking = new DataChecking();

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            switch (command) {
                case (1):
                    monthlyReportsManager.load();
                    continue;
                case (2):
                    yearlyReport.load();
                    continue;
                case (3):
                    dataChecking.checkReports(monthlyReportsManager, yearlyReport);
                    continue;
                case (4):
                    monthlyReportsManager.getInfo();
                    continue;
                case (5):
                    yearlyReport.getInfo();
                    continue;
                case (0):
                    System.out.println("Выход");
                    scanner.close();
                    break;
                default:
                    System.out.println("Такой команды нет. Выберите команду из списка");
                    continue;
            }
            break;
        }
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
