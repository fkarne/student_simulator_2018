package at.tugraz.morning08.a_students_life.data;


public class Event
{
    public Event(String name, Time time, String type, int probability_percentage) {
        this.name = name;
        this.time = time;
        this.type = type;
        this.probability_percentage = probability_percentage;
    }

    public Event(){}

    private String name;
    private Time time;
    private String type;
    private int probability_percentage; // [0; 100]

    public String getName() { return name; }

    public Time getTime() { return time; }

    public String getType() { return type; }

    public int getProbabilityPercentage() { return probability_percentage; }

    public void setName(String name) { this.name = name; }

    public void setTime(Time time) { this.time = time; }

    public void setType(String type) { this.type = type; }

    public void setProbabilityPercentage(int probability_percentage) { this.probability_percentage = probability_percentage; }

    public void increaseProbability(double probabilityChange){
        probability_percentage += probabilityChange;
    }

    public void checkBorderProbability() {
        if (probability_percentage > 100)
            probability_percentage = 100;
    }
}
