package atom.volgatech.model.helper;

import atom.volgatech.model.simulation.SupermarketSimulation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintService {
    static public String _currTime = SupermarketSimulation.getStartSupermarketTime();
    static public String _startTime = SupermarketSimulation.getStartSupermarketTime();
    static private DateFormat _timeFormat = new SimpleDateFormat("HH:mm:ss");
    static private double _time = 0;
    static private long MillsInMinute = 3600000;
    static private double passedHours = 0;

    static public void setUp(String startSupermarketTime) throws ParseException {

        Date timeConst = _timeFormat.parse(startSupermarketTime);
        _startTime = startSupermarketTime;
        _currTime = startSupermarketTime;
        _time = timeConst.getTime();
    }

    public static void updatePassedTime(double hours) throws ParseException {
        passedHours = hours;
        Date timeConst = _timeFormat.parse(_startTime);
        long time = timeConst.getTime();
        Date currentDate = new Date((long) (time + ( passedHours * MillsInMinute)));
        _currTime = _timeFormat.format(currentDate);
    }

    public static String getTimefromDouble(double hours) {
        Date timeConst = null;
        try {
            timeConst = _timeFormat.parse(_startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = timeConst.getTime();
        Date currentDate = new Date((long) (time + ( hours * MillsInMinute)));
        return _timeFormat.format(currentDate);
    }

    static public String getTimeAndUpdate(){
        return _currTime;
    }
}
