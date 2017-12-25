package atom.volgatech;

import atom.volgatech.model.simulation.SupermarketSimulation;
import atom.volgatech.model.supermarket.Supermarket;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        try {
            Supermarket supermarket = new Supermarket();
            SupermarketSimulation supermarketSimulation = new SupermarketSimulation(supermarket);
            supermarketSimulation.processCustomersPool();
            if (supermarketSimulation.isIsFinished()) {
                supermarketSimulation.getReport();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            assert false;
        }
    }
}
