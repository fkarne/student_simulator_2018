package at.tugraz.morning08.a_students_life.data;


public final class Activities
{
    //Main Money
    public static void askForMoney(Student student) {
        student.addTimeUnits(2); // !!! Has to be made first at every Activities!!!

        student.addCash(100);
    }

    //Main Stress
    public static void watchTV(Student student) {
        student.addTimeUnits(2); // !!! Has to be made first at every Activities!!!

        student.getStats().increaseStress(12);
    }
}
