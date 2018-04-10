package at.tugraz.morning08.a_students_life.data;

import java.util.ArrayList;

public class Calendar
{
    private static Calendar instance = null;

    public static Calendar getInstance() {
        if (instance == null) {
            instance = new Calendar();
        }
        return instance;
    }

    private ArrayList<Event> event_list = new ArrayList<>();


    public Event getNextEvent()
    {
        return event_list.get(0);
    }

    public Event getEventAt(int event_position)
    {
        return event_list.get(event_position);
    }

    public void createEvent(String name, int day, int timeUnit, String type)
    {
        event_list.add(new Event(name, day, timeUnit, type));
    }




}
