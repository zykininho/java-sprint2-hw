import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {

    boolean isLoaded;
    ArrayList<YearlyReportRecord> records = new ArrayList<>();
    int year;

    public YearlyReport(int year) {
        this.year = year;
        this.isLoaded = false;
    }

    public void load() {
        SimpleFileReader reader = new SimpleFileReader();
        String content = reader.readFileContentsOrNull("resources/y.2021.csv");
        if (!(content == null)) {
            String[] lines = content.split("\n");
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] parts = line.split(",");
                int month = Integer.parseInt(parts[0]);
                int amount = Integer.parseInt(parts[1]);
                boolean isExpense = Boolean.parseBoolean(parts[2]);
                YearlyReportRecord record = new YearlyReportRecord(month, amount, isExpense);
                records.add(record);
            }
            this.isLoaded = true;
            System.out.println("Годовой отчёт успешно прочитан");
        }
    }

    public void getInfo() {
        if (!isLoaded) {
            System.out.println("Не загружен годовой отчёт");
            return;
        }
        System.out.println(this.year);
        monthlyDiff();
        averageExpenseAndIncome();
    }

    public void monthlyDiff() {

        HashMap<Integer, Integer> monthDiff = new HashMap<>();
        for (YearlyReportRecord record : this.records) {
            if (monthDiff.containsKey(record.month)) {
                int sum = monthDiff.get(record.month);
                monthDiff.put(record.month, sum + (record.isExpense ? (-record.amount) : record.amount));
            } else {
                monthDiff.put(record.month, (record.isExpense ? (-record.amount) : record.amount));
            }
        }
        for (Integer month : monthDiff.keySet()) {
            int sumDiff = monthDiff.get(month);
            System.out.println((sumDiff > 0 ? "Прибыль" : "Убыток") + " в месяце " + month + " - " + sumDiff);
        }

    }

    public void averageExpenseAndIncome() {

        int sumExpense = 0;
        int numberOfMonthExpense = 0;
        int sumIncome = 0;
        int numberOfMonthIncome = 0;
        for (YearlyReportRecord record : this.records) {
            if (record.isExpense) {
                sumExpense += record.amount;
                numberOfMonthExpense++;
            } else {
                sumIncome += record.amount;
                numberOfMonthIncome++;
            }
        }
        System.out.println("Средний расход за все месяцы в году - " + (100 * sumExpense / numberOfMonthExpense / 100.0));
        System.out.println("Средний доход за все месяцы в году - " + (100 * sumIncome / numberOfMonthIncome) / 100.0);

    }

}
