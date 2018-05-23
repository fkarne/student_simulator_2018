package at.tugraz.morning08.a_students_life.data;

import java.util.Random;

public final class EventActions {

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
}
