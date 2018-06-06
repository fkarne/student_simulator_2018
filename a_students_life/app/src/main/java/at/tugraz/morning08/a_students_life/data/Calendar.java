package at.tugraz.morning08.a_students_life.data;

import java.util.ArrayList;
import java.util.Collections;

public class Calendar
{
    private static Calendar instance = null;
    public ArrayList<Event> event_list = new ArrayList<>();

    public static Calendar getInstance() {
        if (instance == null) {
            instance = new Calendar();
        }
        return instance;
    }

    public ArrayList<Event> getEventList() {
        return event_list;
    }

    public Event getNextEvent()
    {
        Event event = null;
        if(!event_list.isEmpty()){
            event = event_list.get(0);
        }
        return event;
    }

    public Event getEventAt(int event_position)
    {
        Event event = null;
        if(event_list.size() > event_position){
            event = event_list.get(event_position);
        }
        return event;
    }

    public void addEvent(Event event)
    {
        event_list.add(event);
    }

    public void clear()
    {
        event_list.clear();
    }

    public void sortEvents() { Collections.sort(event_list); }

    public void removeEvent(Event event){ event_list.remove(event); }
}
