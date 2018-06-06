package at.tugraz.morning08.a_students_life.data;

import java.util.ArrayList;
import java.util.List;

import at.tugraz.morning08.a_students_life.R;
import at.tugraz.morning08.a_students_life.components.ButtonInfo;

public final class Activities
{
    private static int MAX = 100;
    private static int MIN = 0;
    private static float MAX_MULT = 2;
    private static float MIN_MULT = 0.5f;

    public static List<ButtonInfo> energy = new ArrayList<>();
    public static List<ButtonInfo> stress = new ArrayList<>();
    public static List<ButtonInfo> hunger = new ArrayList<>();
    public static List<ButtonInfo> money = new ArrayList<>();
    public static List<ButtonInfo> social = new ArrayList<>();
    public static List<ButtonInfo> study = new ArrayList<>();

    private static boolean init = false;

    //Main Money
    public static boolean askForMoney(Student student) {
        student.addTimeUnits(2); // !!! Has to be made first at every Activities!!!
        student.addCash(100);
        checkBorder(student);
        return true;
    }

    //Main Stress
    public static boolean watchTV(Student student) {
        student.addTimeUnits(2);
        checkBorderMultiplicators(student);

        // TODO: round?
        float stress = 12 *student.getStats().getStress_multiplicator();

        student.getStats().increaseStress((int) stress);
        checkBorder(student);
        return true;
    }

    //Main Social
    public static boolean phoneCall(Student student) {
        student.addTimeUnits(2);
        checkBorderMultiplicators(student);

        float social = 12 *student.getStats().getSocial_multiplicator();

        student.getStats().increaseSocial((int) social);
        checkBorder(student);
        return true;
    }

    //Main Hunger
    public static boolean eat(Student student) {
        student.addTimeUnits(2);
        checkBorderMultiplicators(student);

        float hunger = 12 *student.getStats().getHunger_multiplicator();

        student.getStats().increaseHunger((int) hunger);
        checkBorder(student);
        return true;
    }

    //Main Energy
    public static boolean sleep(Student student) {
        student.addTimeUnits(16);
        checkBorderMultiplicators(student);

        float energy = 66 *student.getStats().getEnergy_multiplicator();

        student.getStats().increaseEnergy((int) energy);
        checkBorder(student);
        return true;
    }

    //Main Study
    public static boolean learn(Student student) {
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
        return true;
    }

    //Sub Energy
    public static boolean nap(Student student) {
        student.addTimeUnits(2);
        checkBorderMultiplicators(student);

        float energy = 12 *student.getStats().getEnergy_multiplicator();

        student.getStats().increaseEnergy((int) energy);
        checkBorder(student);
        return true;
    }

    //Sub Hunger
    public static boolean goingOutToEat(Student student) {
        int cost = 50;
        if(checkMoney(student, cost)) {
            student.addTimeUnits(4);
            checkBorderMultiplicators(student);

            float social = 9 * student.getStats().getSocial_multiplicator();
            float hunger = 19 * student.getStats().getHunger_multiplicator();

            student.getStats().increaseHunger((int) hunger);
            student.getStats().increaseSocial((int) social);
            student.addCash(-cost);
            checkBorder(student);
            return true;
        }
        return false;
    }

    //Sub Stress
    public static boolean readingBook(Student student) {
        student.addTimeUnits(1);
        checkBorderMultiplicators(student);

        float stress = 6 *student.getStats().getStress_multiplicator();

        student.getStats().increaseStress((int)stress);
        checkBorder(student);
        return true;
    }

    //Sub Social
    public static boolean partying(Student student) {
        student.addTimeUnits(12);
        checkBorderMultiplicators(student);

        float social = 62 *student.getStats().getSocial_multiplicator();
        float energy_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getEnergy_multiplicator());
        float energy = 18 * energy_conjugated;

        student.getStats().increaseSocial((int)social);
        student.getStats().decreaseEnergy((int)energy);
        checkBorder(student);
        return true;
    }

    //Sub Social
    public static boolean meetFriends(Student student) {
        student.addTimeUnits(4);
        checkBorderMultiplicators(student);

        float social = 24 *student.getStats().getSocial_multiplicator();
        float stress = 9 *student.getStats().getStress_multiplicator();

        student.getStats().increaseSocial((int) social);
        student.getStats().increaseStress((int)stress);
        checkBorder(student);
        return true;
    }

    //Sub Stress
    public static boolean sports(Student student) {
        student.addTimeUnits(3);
        checkBorderMultiplicators(student);

        float stress = 18 *student.getStats().getStress_multiplicator();
        float energy_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getEnergy_multiplicator());
        float energy = 7 * energy_conjugated;

        student.getStats().increaseStress((int)stress);
        student.getStats().decreaseEnergy((int)energy);
        checkBorder(student);
        return true;
    }

    //Sub Hunger
    public static boolean snack(Student student) {
        int cost = 20;
        if(checkMoney(student, cost)) {
            student.addTimeUnits(1);
            checkBorderMultiplicators(student);

            float hunger = 8 * student.getStats().getHunger_multiplicator();

            student.getStats().increaseHunger((int) hunger);
            student.addCash(-cost);
            checkBorder(student);
            return true;
        }
        return false;
    }

    public static void visitLecture(Student student, Event lecture) {
        if(lecture.getTime().getDay() == Student.getInstance().getTime().getDay() &&
                Student.getInstance().getTime().getTimeUnit() >= lecture.getTime().getTimeUnit() - 4  &&
                Student.getInstance().getTime().getTimeUnit() <= lecture.getTime().getTimeUnit() &&
                lecture.getType() == Event.Type.Lecture) {

            student.addTimeUnits(4 + (lecture.getTime().getTimeUnit() - student.getTime().getTimeUnit()));
            checkBorderMultiplicators(student);

            lecture.getExam().increaseProbability(20);
            lecture.getExam().checkBorderProbability();

            double energy_conjugated = student.getStats().getConjugatedMultiplicator(student.getStats().getEnergy_multiplicator());
            double energy = 4.0 * energy_conjugated;

            double stress = 7.0 * student.getStats().getStress_multiplicator();

            student.getStats().decreaseEnergy((int) energy);
            student.getStats().increaseStress((int) stress);
            checkBorder(student);
        }
    }

    public static void checkBorder(Student student) {
        student.getStats().setEnergy(checkBorderStat(student.getStats().getEnergy()));
        student.getStats().setHunger(checkBorderStat(student.getStats().getHunger()));
        student.getStats().setStress(checkBorderStat(student.getStats().getStress()));
        student.getStats().setSocial(checkBorderStat(student.getStats().getSocial()));
    }

    private static int checkBorderStat(int stat) {
        return stat > MAX ? MAX : (stat < MIN ? MIN : stat);
    }

    public static void checkBorderMultiplicators(Student student) {
        student.getStats().setEnergy_multiplicator(checkBorderMults(student.getStats().getEnergy_multiplicator()));
        student.getStats().setHunger_multiplicator(checkBorderMults(student.getStats().getHunger_multiplicator()));
        student.getStats().setStress_multiplicator(checkBorderMults(student.getStats().getStress_multiplicator()));
        student.getStats().setSocial_multiplicator(checkBorderMults(student.getStats().getSocial_multiplicator()));
    }

    private static float checkBorderMults(float multiplicator) {
        return multiplicator > MAX_MULT ? MAX_MULT : (multiplicator < MIN_MULT ? MIN_MULT : multiplicator);
    }

    private static boolean checkMoney(Student student, int money) {
        return student.getCash() >= money;
    }

    public static void createButtonInfo() {
        if(!init) {
            energy.add(new ButtonInfo(ActivityEnum.SLEEP, R.string.activity_sleep));
            energy.add(new ButtonInfo(ActivityEnum.NAP, R.string.activity_powerNap));

            hunger.add(new ButtonInfo(ActivityEnum.EAT, R.string.activity_eat));
            hunger.add(new ButtonInfo(ActivityEnum.GOINGOUTTOEAT, R.string.activity_eatOutside));
            hunger.add(new ButtonInfo(ActivityEnum.SNACK, R.string.activity_snack));

            money.add(new ButtonInfo(ActivityEnum.ASKFORMONEY, R.string.activity_askForMoney));

            social.add(new ButtonInfo(ActivityEnum.PHONECALL, R.string.activity_callFriends));
            social.add(new ButtonInfo(ActivityEnum.PARTYING, R.string.activity_party));
            social.add(new ButtonInfo(ActivityEnum.MEETFRIENDS, R.string.activity_meetFriends));

            stress.add(new ButtonInfo(ActivityEnum.WATCHTV, R.string.activity_watchTv));
            stress.add(new ButtonInfo(ActivityEnum.READINGBOOK, R.string.activity_readBook));
            stress.add(new ButtonInfo(ActivityEnum.SPORTS, R.string.activity_doSports));

            study.add(new ButtonInfo(ActivityEnum.LEARN, R.string.activity_study));
            init = true;
        }
    }
}
