package atom.volgatech.model.cash_desk;

public class Bill implements BillInterface {
    public Bill() {
        _sum = 0;
    }

    public void addToCheque(double addition) {
        _sum += addition;
    }

    public double getBill() {
        return _sum;
    }

    public boolean isEmpty() {
        return _sum == 0;
    }

    private double _sum;
}
