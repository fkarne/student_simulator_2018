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
        int cash = (random.nextInt(3) + 3) * 300; //900-1500

        student.addCash(cash);
    }

    //Random Events Hunger
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

    //Random Events Social
    public static void meetOldFriend (Student student) {
        student.addTimeUnits(2);
        checkBorderMultiplicators(student);

        float social = 22 *student.getStats().getSocial_multiplicator();

        student.getStats().increaseSocial((int) social);
        checkBorder(student);
    }

    public static void blindDate (Student student) {
        student.addTimeUnits(6);
        checkBorderMultiplicators(student);

        float social = 36 *student.getStats().getSocial_multiplicator();
        float stress_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getStress_multiplicator());
        float stress = 14 * stress_conjugated;

        student.getStats().increaseSocial((int) social);
        student.getStats().decreaseStress((int) stress);
        checkBorder(student);
    }

    public static void onACoffeeWithParents (Student student) {
        student.addTimeUnits(4);
        checkBorderMultiplicators(student);

        float social = 14 *student.getStats().getSocial_multiplicator();

        student.getStats().increaseSocial((int) social);
        checkBorder(student);
    }

    public static void installedWorldOfWarcraft (Student student) {
        student.addTimeUnits(10);
        checkBorderMultiplicators(student);

        float social_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getSocial_multiplicator());
        float social = 30 * social_conjugated;

        student.getStats().decreaseSocial((int) social);
        checkBorder(student);
    }

    //Random Events Energy
    public static void fellAsleepDuringLearning (Student student) {
        student.addTimeUnits(6);
        checkBorderMultiplicators(student);

        float energy = 26 *student.getStats().getEnergy_multiplicator();

        student.getStats().increaseEnergy((int) energy);
        checkBorder(student);
    }

    public static void invitedToWorkOut (Student student) {
        student.addTimeUnits(3);
        checkBorderMultiplicators(student);

        float energy_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getEnergy_multiplicator());
        float energy = 17 * energy_conjugated;

        student.getStats().decreaseEnergy((int) energy);
        checkBorder(student);
    }

    //Pop-Up says something along the lines of: Your Mum wishes you all the best for your next exam
    public static void youCanDoThis (Student student) {
        checkBorderMultiplicators(student);

        float energy = 20 *student.getStats().getEnergy_multiplicator();

        student.getStats().increaseEnergy((int) energy);
        checkBorder(student);
    }

    public static void sleeptVeryWellToday (Student student) {
        checkBorderMultiplicators(student);

        float energy = 10 *student.getStats().getEnergy_multiplicator();

        student.getStats().increaseEnergy((int) energy);
        checkBorder(student);
    }

    //Random Events Stress
    public static void meditate (Student student) {
        student.addTimeUnits(2);
        checkBorderMultiplicators(student);

        float stress = 17 *student.getStats().getStress_multiplicator();

        student.getStats().increaseStress((int) stress);
        checkBorder(student);
    }

    public static void familyProblems (Student student) {
        checkBorderMultiplicators(student);

        float stress_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getStress_multiplicator());
        float stress = 20 * stress_conjugated;

        student.getStats().decreaseStress((int) stress);
        checkBorder(student);
    }

    public static void someoneWithAnOpenEar (Student student) {
        student.addTimeUnits(3);
        checkBorderMultiplicators(student);

        float stress = 23 *student.getStats().getStress_multiplicator();

        student.getStats().increaseStress((int) stress);
        checkBorder(student);
    }

    public static void goForAWalk (Student student) {
        student.addTimeUnits(2);
        checkBorderMultiplicators(student);

        float stress = 32 *student.getStats().getStress_multiplicator();

        student.getStats().increaseStress((int) stress);
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
