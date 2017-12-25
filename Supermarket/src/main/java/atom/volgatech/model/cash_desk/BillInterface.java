package atom.volgatech.model.cash_desk;

public interface BillInterface {
    void addToCheque(double addition);
    double getBill();
    boolean isEmpty();
}
