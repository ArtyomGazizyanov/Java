package atom.volgatech.model.report;

public class PeriodReport {
    public PeriodReport()
    {
        _periodSum = 0;
    }

    public void add(double addition)
    {
        _periodSum += addition;
    }

    public double get() {
        return _periodSum;
    }

    private double _periodSum;
}
