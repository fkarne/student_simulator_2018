package at.tugraz.morning08.a_students_life.data;

public enum ActivityEnum {
    SLEEP("sleep"),
    NAP("nap"),
    EAT("eat"),
    GOINGOUTTOEAT("goingOutToEat"),
    SNACK("snack"),
    ASKFORMONEY("askForMoney"),
    PHONECALL("phoneCall"),
    PARTYING("partying"),
    MEETFRIENDS("meetFriends"),
    WATCHTV("watchTV"),
    READINGBOOK("readingBook"),
    SPORTS("sports"),
    LEARN("learn");

    private String text;

    ActivityEnum(final String text)
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return text;
    }
}
