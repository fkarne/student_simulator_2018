package at.tugraz.morning08.a_students_life.data;

public class Time {
    private static int MAX_TIMEUNITS = 48; //One TimeUnit is 30 mins

    private int day = 1;
    private int timeUnit = 16;

    public Time() {
    }

    public Time(int day, int timeUnit) {
        this.day = day;
        this.timeUnit = timeUnit;
    }

    public int getDay() {
        return day;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setTimeUnit(int timeUnit) {
        this.timeUnit = timeUnit;
    }

    public void addTimeUnits(int timeUnitsToAdd) {
        timeUnit += timeUnitsToAdd;
        day += timeUnit / MAX_TIMEUNITS;
        timeUnit = timeUnit % MAX_TIMEUNITS;
    }

    public String getTimeString()
    {
        String hour = String.valueOf(timeUnit/(MAX_TIMEUNITS/24));
        String minutes = "00";

        if (timeUnit%(MAX_TIMEUNITS/24) != 0)
        {

            minutes = String.valueOf(timeUnit%(MAX_TIMEUNITS/24)*((24*60)/MAX_TIMEUNITS));
        }
        hour = ("0" + hour).substring(hour.length() - 1);
        return hour + ":" + minutes;

    }
}
