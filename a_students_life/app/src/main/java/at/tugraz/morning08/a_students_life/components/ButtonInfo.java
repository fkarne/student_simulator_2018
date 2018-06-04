package at.tugraz.morning08.a_students_life.components;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import at.tugraz.morning08.a_students_life.data.ActivityEnum;
import at.tugraz.morning08.a_students_life.data.Student;

public class ButtonInfo {
    private ActivityEnum activity;
    private int infoKey;

    public ButtonInfo(ActivityEnum method, int infoKey) {
        this.activity = method;
        this.infoKey = infoKey;
    }

    public boolean invokeMethod(){
        try {
            Class c = Class.forName("at.tugraz.morning08.a_students_life.data.Activities");
            Method method = c.getDeclaredMethod(this.activity.toString(), Student.class);
            return (boolean) method.invoke(c, Student.getInstance());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getInfoKey() {
        return infoKey;
    }

    public ActivityEnum getActivity() {
        return activity;
    }
}
