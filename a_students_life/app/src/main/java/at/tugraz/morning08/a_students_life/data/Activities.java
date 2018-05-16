package at.tugraz.morning08.a_students_life.data;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import at.tugraz.morning08.a_students_life.components.MyButton;

public final class Activities
{
    private static int MAX = 100;
    private static int MIN = 0;
    private static float MAX_MULT = 2;
    private static float MIN_MULT = 0.5f;

    public static List<MyButton> energy = new ArrayList<>();
    public static List<MyButton> stress = new ArrayList<>();
    public static List<MyButton> hunger = new ArrayList<>();
    public static List<MyButton> money = new ArrayList<>();
    public static List<MyButton> social = new ArrayList<>();
    public static List<MyButton> study = new ArrayList<>();

    private static boolean init = false;

    //Main Money
    public static void askForMoney(Student student) {
        student.addTimeUnits(2); // !!! Has to be made first at every Activities!!!
        student.addCash(100);
        checkBorder(student);
    }

    //Main Stress
    public static void watchTV(Student student) {
        student.addTimeUnits(2);
        checkBorderMultiplicators(student);

        // TODO: round?
        float stress = 12 *student.getStats().getStress_multiplicator();

        student.getStats().increaseStress((int) stress);
        checkBorder(student);
    }

    //Main Social
    public static void phoneCall(Student student) {
        student.addTimeUnits(2);
        checkBorderMultiplicators(student);

        float social = 12 *student.getStats().getSocial_multiplicator();

        student.getStats().increaseSocial((int) social);
        checkBorder(student);
    }

    //Main Hunger
    public static void eat(Student student) {
        student.addTimeUnits(2);
        checkBorderMultiplicators(student);

        float hunger = 12 *student.getStats().getHunger_multiplicator();

        student.getStats().increaseHunger((int) hunger);
        checkBorder(student);
    }

    //Main Energy
    public static void sleep(Student student) {
        student.addTimeUnits(16);
        checkBorderMultiplicators(student);

        float energy = 66 *student.getStats().getEnergy_multiplicator();

        student.getStats().increaseEnergy((int) energy);
        checkBorder(student);
    }

    //Main Study
    public static void learn(Student student) {
        Event event = student.getNextExam();
        if(event != null) {
            student.addTimeUnits(4);
            checkBorderMultiplicators(student);

            //TODO probability depends on event difficulty
            event.increaseProbability(20);
            event.checkBorderProbability();

            float energy_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getEnergy_multiplicator());
            float energy = 4 * energy_conjugated;

            float stress = 7 * student.getStats().getStress_multiplicator();

            student.getStats().decreaseEnergy((int) energy);
            student.getStats().increaseStress((int) stress);
            checkBorder(student);
        }
    }

    //Sub Energy
    public static void nap(Student student) {
        student.addTimeUnits(2);
        checkBorderMultiplicators(student);

        float energy = 12 *student.getStats().getEnergy_multiplicator();

        student.getStats().increaseEnergy((int) energy);
        checkBorder(student);
    }

    //Sub Hunger
    public static void goingOutToEat(Student student) {
        student.addTimeUnits(4);
        checkBorderMultiplicators(student);

        float social = 9 *student.getStats().getSocial_multiplicator();
        float hunger = 19 *student.getStats().getHunger_multiplicator();

        student.getStats().increaseHunger((int) hunger);
        student.getStats().increaseSocial((int)social);
        student.addCash(-50);
        checkBorder(student);
    }

    //Sub Stress
    public static void readingBook(Student student) {
        student.addTimeUnits(1);
        checkBorderMultiplicators(student);

        float stress = 6 *student.getStats().getStress_multiplicator();

        student.getStats().increaseStress((int)stress);
        checkBorder(student);
    }

    //Sub Social
    public static void partying(Student student) {
        student.addTimeUnits(12);
        checkBorderMultiplicators(student);

        float social = 62 *student.getStats().getSocial_multiplicator();
        float energy_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getEnergy_multiplicator());
        float energy = 18 * energy_conjugated;

        student.getStats().increaseSocial((int)social);
        student.getStats().decreaseEnergy((int)energy);
        checkBorder(student);
    }

    //Sub Social
    public static void meetFriends(Student student) {
        student.addTimeUnits(4);
        checkBorderMultiplicators(student);

        float social = 24 *student.getStats().getSocial_multiplicator();
        float stress = 9 *student.getStats().getStress_multiplicator();

        student.getStats().increaseSocial((int) social);
        student.getStats().increaseStress((int)stress);
        checkBorder(student);
    }

    //Sub Stress
    public static void sports(Student student) {
        student.addTimeUnits(3);
        checkBorderMultiplicators(student);

        float stress = 18 *student.getStats().getStress_multiplicator();
        float energy_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getEnergy_multiplicator());
        float energy = 7 * energy_conjugated;

        student.getStats().increaseStress((int)stress);
        student.getStats().decreaseEnergy((int)energy);
        checkBorder(student);
    }

    //Sub Hunger
    public static void snack(Student student) {
        student.addTimeUnits(1);
        checkBorderMultiplicators(student);

        float hunger = 8 *student.getStats().getHunger_multiplicator();

        student.getStats().increaseHunger((int) hunger);
        student.addCash(-20);
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

    public static void createButtons(Context context) {
        if(!init) {
            energy.add(new MyButton(context, "sleep", "@string/activity_sleep"));
            energy.add(new MyButton(context, "nap", "@string/activity_powerNap"));
            hunger.add(new MyButton(context, "eat", "@string/activity_eat"));
            hunger.add(new MyButton(context, "goingOutToEat", "@string/activity_eatOutside"));
            hunger.add(new MyButton(context, "snack", "@string/activity_snack"));
            money.add(new MyButton(context, "askForMoney", "@string/activity_askForMoney"));
            social.add(new MyButton(context, "phoneCall", "@string/activity_callFriends"));
            social.add(new MyButton(context, "partying", "@string/activity_party"));
            social.add(new MyButton(context, "meetFriends", "@string/activity_meetFriends"));
            stress.add(new MyButton(context, "watchTV", "@string/activity_watchTv"));
            stress.add(new MyButton(context, "readingBook", "@string/activity_readBook"));
            stress.add(new MyButton(context, "sports", "@string/activity_doSports"));
            study.add(new MyButton(context, "learn", "@string/activity_study"));
            init = true;
        }
    }
}
