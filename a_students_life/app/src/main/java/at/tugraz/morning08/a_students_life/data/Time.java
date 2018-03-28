package at.tugraz.morning08.a_students_life.data;

public class Time {
    private static int MAX_TIMEUNITS = 48; //One TimeUnit is 30 mins

    private int day = 1;
    private int timeUnit = 16;

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
}
