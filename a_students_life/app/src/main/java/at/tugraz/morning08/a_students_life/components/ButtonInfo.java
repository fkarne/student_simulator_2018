package at.tugraz.morning08.a_students_life.components;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import at.tugraz.morning08.a_students_life.data.Student;

public class ButtonInfo {
    private String method;
    private int infoKey;

    public ButtonInfo(String method, int infoKey) {
        this.method = method;
        this.infoKey = infoKey;
    }

    public void invokeMethod(){
        try {
            Class c = Class.forName("at.tugraz.morning08.a_students_life.data.Activities");
            Method method = c.getDeclaredMethod(this.method, Student.class);
            method.invoke(c, Student.getInstance());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public int getInfoKey() {
        return infoKey;
    }
}