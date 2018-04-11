package at.tugraz.morning08.a_students_life.data;


public final class Activities
{
    public static void askForMoney(Student student) {
        student.addTimeUnits(2); // !!! Has to be made first at every Activities!!!

        student.addCash(100);
    }
}
