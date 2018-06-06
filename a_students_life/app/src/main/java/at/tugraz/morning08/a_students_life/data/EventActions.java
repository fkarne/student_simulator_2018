package at.tugraz.morning08.a_students_life.data;

import java.util.Random;

public final class EventActions {
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
        Activities.checkBorderMultiplicators(student);
        float hunger = 5 *student.getStats().getHunger_multiplicator();

        student.getStats().increaseHunger((int) hunger);
        Activities.checkBorder(student);
    }

    public static void invitedToEat (Student student) {
        student.addTimeUnits(4);
        Activities.checkBorderMultiplicators(student);

        float hunger = 29 *student.getStats().getHunger_multiplicator();

        student.getStats().increaseHunger((int) hunger);
        Activities.checkBorder(student);
    }

    public static void visitFastFoodRestaurant (Student student) {
        int cost = 25;
        if(Activities.checkMoney(student, cost)) {
            student.addTimeUnits(1);
            Activities.checkBorderMultiplicators(student);

            float hunger = 16 * student.getStats().getHunger_multiplicator();
            student.getStats().increaseHunger((int) hunger);
            student.addCash(-cost);
            Activities.checkBorder(student);
        }
    }

    public static void freeFoodDayAtUniCampus (Student student) {
        student.addTimeUnits(2);
        Activities.checkBorderMultiplicators(student);

        float hunger = 17 *student.getStats().getHunger_multiplicator();

        student.getStats().increaseHunger((int) hunger);
        Activities.checkBorder(student);
    }

    //Random Events Social
    public static void meetOldFriend (Student student) {
        student.addTimeUnits(2);
        Activities.checkBorderMultiplicators(student);

        float social = 22 *student.getStats().getSocial_multiplicator();

        student.getStats().increaseSocial((int) social);
        Activities.checkBorder(student);
    }

    public static void blindDate (Student student) {
        student.addTimeUnits(6);
        Activities.checkBorderMultiplicators(student);

        float social = 36 *student.getStats().getSocial_multiplicator();
        float stress_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getStress_multiplicator());
        float stress = 14 * stress_conjugated;

        student.getStats().increaseSocial((int) social);
        student.getStats().decreaseStress((int) stress);
        Activities.checkBorder(student);
    }

    public static void onACoffeeWithParents (Student student) {
        student.addTimeUnits(4);
        Activities.checkBorderMultiplicators(student);

        float social = 14 *student.getStats().getSocial_multiplicator();

        student.getStats().increaseSocial((int) social);
        Activities.checkBorder(student);
    }

    public static void installedWorldOfWarcraft (Student student) {
        student.addTimeUnits(10);
        Activities.checkBorderMultiplicators(student);

        float social_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getSocial_multiplicator());
        float social = 20 * social_conjugated;

        student.getStats().decreaseSocial((int) social);
        Activities.checkBorder(student);
    }

    //Random Events Energy
    public static void fellAsleepDuringLearning (Student student) {
        student.addTimeUnits(6);
        Activities.checkBorderMultiplicators(student);

        float energy = 26 *student.getStats().getEnergy_multiplicator();
        float stress_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getStress_multiplicator());
        float stress = 10 * stress_conjugated;

        student.getStats().increaseEnergy((int) energy);
        student.getStats().decreaseStress((int) stress);
        Activities.checkBorder(student);
    }

    public static void invitedToWorkOut (Student student) {
        student.addTimeUnits(3);
        Activities.checkBorderMultiplicators(student);

        float energy_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getEnergy_multiplicator());
        float energy = 7 * energy_conjugated;

        student.getStats().decreaseEnergy((int) energy);
        Activities.checkBorder(student);
    }

    //Pop-Up says something along the lines of: Your Mum wishes you all the best for your next exam
    public static void youCanDoThis (Student student) {
        Activities.checkBorderMultiplicators(student);

        float energy = 20 * student.getStats().getEnergy_multiplicator();

        student.getStats().increaseEnergy((int) energy);
        Activities.checkBorder(student);
    }

    public static void sleeptVeryWellToday (Student student) {
        Activities.checkBorderMultiplicators(student);

        float energy = 10 *student.getStats().getEnergy_multiplicator();

        student.getStats().increaseEnergy((int) energy);
        Activities.checkBorder(student);
    }

    //Random Events Stress
    public static void meditate (Student student) {
        student.addTimeUnits(2);
        Activities.checkBorderMultiplicators(student);

        float stress = 17 *student.getStats().getStress_multiplicator();

        student.getStats().increaseStress((int) stress);
        Activities.checkBorder(student);
    }

    public static void familyProblems (Student student) {
        Activities.checkBorderMultiplicators(student);

        float stress_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getStress_multiplicator());
        float stress = 20 * stress_conjugated;

        student.getStats().decreaseStress((int) stress);
        Activities.checkBorder(student);
    }

    public static void someoneWithAnOpenEar (Student student) {
        student.addTimeUnits(3);
        Activities.checkBorderMultiplicators(student);

        float stress = 23 *student.getStats().getStress_multiplicator();

        student.getStats().increaseStress((int) stress);
        Activities.checkBorder(student);
    }

    public static void goForAWalk (Student student) {
        student.addTimeUnits(2);
        Activities.checkBorderMultiplicators(student);

        float stress = 12 *student.getStats().getStress_multiplicator();

        student.getStats().increaseStress((int) stress);
        Activities.checkBorder(student);
    }
}
