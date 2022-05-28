import java.util.ArrayList;

public class MonthlyReport {

    ArrayList<MonthlyReportRecord> records = new ArrayList<>();

    public void add(MonthlyReportRecord record) {
        records.add(record);
    }

}