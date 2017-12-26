package atom.volgatech.model.cash_desk;

import atom.volgatech.model.customer.Customer;
import atom.volgatech.model.report.PeriodReport;

public interface CashDeskInterface {
    void processPoolQueue(Customer customer);
    //добавить параметр, который будет говорить о периоде отчета
    //убрать слово интерфейс
    PeriodReport getPeriodReport();
}
