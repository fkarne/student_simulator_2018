package at.tugraz.morning08.a_students_life.components;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import at.tugraz.morning08.a_students_life.data.Student;

public class MyButton extends AppCompatButton {
    private  String method;

    public MyButton(Context context, String method, String infoKey) {
        super(context);
        this.method = method;
        this.setText(infoKey);
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
}
