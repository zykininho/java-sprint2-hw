public class DataChecking {

    public void checkReports(MonthlyReportsManager monthlyReportsManager, YearlyReport yearlyReport) {

        if (!monthlyReportsManager.isLoaded) {
            System.out.println("Не загружены отчёты по месяцам");
            return;
        } else if (!yearlyReport.isLoaded) {
            System.out.println("Не загружен годовой отчёт");
            return;
        }

        boolean isEquals = true;
        for (int i = 0; i < monthlyReportsManager.monthlyReports.size(); i++) {
            MonthlyReport monthlyReport = monthlyReportsManager.monthlyReports.get(i);
            // считаем по каждому месяцу суммы дохода и расхода
            int sumExpense = 0;
            int sumIncome = 0;
            for (MonthlyReportRecord record : monthlyReport.records) {
                if (record.isExpense) {
                    sumExpense += (record.quantity * record.sumOfOne);
                } else {
                    sumIncome += (record.quantity * record.sumOfOne);
                }
            }
            // сравниваем отдельно каждую запись по месяцу по суммам дохода и расхода
            for (YearlyReportRecord record : yearlyReport.records) {
                if (record.month == (i + 1)) {
                    if (record.isExpense) {
                        if (!(record.amount == sumExpense)) {
                            isEquals = false;
                            System.out.println("В месяце " + (i + 1) + " найдено несоответствие при сверке расходов");
                        }
                    } else {
                        if (!(record.amount == sumIncome)) {
                            isEquals = false;
                            System.out.println("В месяце " + (i + 1) + " найдено несоответствие при сверке доходов");
                        }
                    }
                }
            }
        }
        if (isEquals) {
            System.out.println("Сверка отчётов успешно завершена");
        }
    }

}
