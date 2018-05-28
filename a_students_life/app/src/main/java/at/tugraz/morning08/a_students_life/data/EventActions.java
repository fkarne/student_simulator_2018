package at.tugraz.morning08.a_students_life.data;

import java.util.Random;

public final class EventActions {
    private static int MAX = 100;
    private static int MIN = 0;
    private static float MAX_MULT = 2;
    private static float MIN_MULT = 0.5f;

    //Random Money Events
    public static void lotteryWin (Student student){
        student.addCash(10000);
    }

    public static void foundMoney (Student student){

        Random random = new Random();
        int cash = (random.nextInt(4) + 1) * 100; //100-400

        student.addCash(cash);
    }

    public static void moneyFromGrandma (Student student) {

        Random random = new Random();
        int cash = (random.nextInt(6) + 1) * 50; //50-300

        student.addCash(cash);
    }

    public static void inherit (Student student) {

        Random random = new Random();
        int cash = (random.nextInt(6) + 5) * 1000; //5000-10000

        student.addCash(cash);
    }

    public static void helpedAFriend (Student student){

        Random random = new Random();
        int cash = (random.nextInt(3) + 3) * 500; //1500-2500

        student.addCash(cash);
    }

    public static void foundCerealBar (Student student){
        checkBorderMultiplicators(student);
        float hunger = 5 *student.getStats().getHunger_multiplicator();

        student.getStats().increaseHunger((int) hunger);
        checkBorder(student);
    }

    public static void invitedToEat (Student student) {
        student.addTimeUnits(4);
        checkBorderMultiplicators(student);

        float hunger = 29 *student.getStats().getHunger_multiplicator();

        student.getStats().increaseHunger((int) hunger);
        checkBorder(student);
    }

    public static void visitFastFoodRestaurant (Student student) {
        student.addTimeUnits(1);
        checkBorderMultiplicators(student);

        float hunger = 16 *student.getStats().getHunger_multiplicator();

        student.getStats().increaseHunger((int) hunger);
        checkBorder(student);
    }

    public static void freeFoodDayAtUniCampus (Student student) {
        student.addTimeUnits(2);
        checkBorderMultiplicators(student);

        float hunger = 17 *student.getStats().getHunger_multiplicator();

        student.getStats().increaseHunger((int) hunger);
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

    private static void checkBorderMultiplicators(Student student) {
        student.getStats().setEnergy_multiplicator(checkBorderMults(student.getStats().getEnergy_multiplicator()));
        student.getStats().setHunger_multiplicator(checkBorderMults(student.getStats().getHunger_multiplicator()));
        student.getStats().setStress_multiplicator(checkBorderMults(student.getStats().getStress_multiplicator()));
        student.getStats().setSocial_multiplicator(checkBorderMults(student.getStats().getSocial_multiplicator()));
    }

    private static float checkBorderMults(float multiplicator) {
        return multiplicator > MAX_MULT ? MAX_MULT : (multiplicator < MIN_MULT ? MIN_MULT : multiplicator);
    }
}
