package at.tugraz.morning08.a_students_life.data;


public final class Activities
{
    private static int MAX = 100;
    private static int MIN = 0;

    //Main Money
    public static void askForMoney(Student student) {
        student.addTimeUnits(2); // !!! Has to be made first at every Activities!!!
        student.addCash(100);
        checkBorder(student);
    }

    //Main Stress
    public static void watchTV(Student student) {
        student.addTimeUnits(2);
        student.getStats().increaseStress(12);
        checkBorder(student);
    }

    //Main Social
    public static void phoneCall(Student student) {
        student.addTimeUnits(2);
        student.getStats().increaseSocial(12);
        checkBorder(student);
    }

    //Main Hunger
    public static void eat(Student student) {
        student.addTimeUnits(2);
        student.getStats().increaseHunger(12);
        checkBorder(student);
    }

    //Main Energy
    public static void sleep(Student student) {
        student.addTimeUnits(16);
        student.getStats().increaseEnergy(66);
        checkBorder(student);
    }

    //Sub Energy
    public static void nap(Student student) {
        student.addTimeUnits(2);
        student.getStats().increaseEnergy(12);
        checkBorder(student);
    }

    private static void checkBorder(Student student) {
        student.getStats().setEnergy(checkBorderStat(student.getStats().getEnergy()));
        student.getStats().setHunger(checkBorderStat(student.getStats().getHunger()));
        student.getStats().setStress(checkBorderStat(student.getStats().getStress()));
        student.getStats().setSocial(checkBorderStat(student.getStats().getSocial()));
    }

    private static int checkBorderStat(int stat) {
        return stat > MAX ? MAX : (stat < MIN ? MIN : stat);
    }
}
