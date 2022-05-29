import java.io.File;
import java.util.ArrayList;

public class MonthlyReportsManager {

    boolean isLoaded;
    ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();

    public MonthlyReportsManager() {
        this.isLoaded = false;
    }

    public void load() {
        SimpleFileReader reader = new SimpleFileReader();
        for (int m = 1; m <= 3; m++) {
            MonthlyReport report = new MonthlyReport();
            monthlyReports.add(report);
            String content = reader.readFileContentsOrNull("resources" + File.separator + "m.20210" + m + ".csv");
            if (!(content == null)) {
                String[] lines = content.split("\n");
                for (int i = 1; i < lines.length; i++) {
                    String line = lines[i];
                    String[] parts = line.split(",");
                    String itemName = parts[0];
                    boolean isExpense = Boolean.parseBoolean(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    int sumOfOne = Integer.parseInt(parts[3]);
                    MonthlyReportRecord record = new MonthlyReportRecord(itemName, isExpense, quantity, sumOfOne);
                    report.add(record);
                }
                this.isLoaded = true;
                System.out.println("Отчёт по месяцу " + m + " успешно прочитан");
            }
        }
    }

    public void getInfo() {
        if (!isLoaded) {
            System.out.println("Не загружены отчёты по месяцам");
            return;
        }
        for (int i = 1; i <= this.monthlyReports.size(); i++) {
            System.out.println("Месяц " + i);
            MonthlyReport monthlyReport = this.monthlyReports.get(i - 1);
            maxIncomeAndExpenseItem(monthlyReport);
        }

    }

    public void maxIncomeAndExpenseItem(MonthlyReport monthlyReport) {
        int maxAmountIncome = 0;
        String itemNameIncome = "";
        int maxAmountExpense = 0;
        String itemNameExpense = "";
        for (MonthlyReportRecord record : monthlyReport.records) {
            if (!record.isExpense) {
                if ((record.quantity * record.sumOfOne) > maxAmountIncome) {
                    maxAmountIncome = record.quantity * record.sumOfOne;
                    itemNameIncome = record.itemName;
                }
            } else {
                if ((record.quantity * record.sumOfOne) > maxAmountExpense) {
                    maxAmountExpense = record.quantity * record.sumOfOne;
                    itemNameExpense = record.itemName;
                }
            }
        }
        System.out.println("Самый прибыльный товар - " + itemNameIncome + ", на сумму " + maxAmountIncome);
        System.out.println("Самая большая трата - " + itemNameExpense + ", на сумму " + maxAmountExpense);
    }

}
