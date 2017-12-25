package atom.volgatech.model.report;

public interface ReportInterface {
    void increaseCustomersAmount();
    int getCustomersAmount();
    void updateTodaySum(PeriodReport additional);
    double getTodayProfit();
}
