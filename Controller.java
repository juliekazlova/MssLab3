package by.bsu.mss2019.kazlova.dstask3.controller;

import by.bsu.mss2019.kazlova.dstask3.ClassInfo;
import by.bsu.mss2019.kazlova.dstask3.MethodInfo;
import com.sun.jdi.InvocationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class Controller {

    private ObservableList<ClassInfo> classInfos;
    private ObservableList<MethodInfo> methodInfos;
    private  String curClass;

    public ObservableList<ClassInfo> getClassInfos() {
        return classInfos;
    }

    public ObservableList<MethodInfo> getMethodInfos() {
        return methodInfos;
    }

    public void createInfos(){
        classInfos= FXCollections.observableArrayList(new ClassInfo("java.lang.String"), new ClassInfo("java.lang.StringBuilder"),
                new ClassInfo("java.util.Date"));
    }

    public boolean choseClass(ClassInfo classInfo) {
        try {
            Class selectedClass=Class.forName(classInfo.getName());
            curClass=classInfo.getName();
            Method[] methods=selectedClass.getMethods();
            methodInfos=FXCollections.observableArrayList();
            for(Method cur: methods){
                if(cur.getParameterTypes().length==0) {
                    MethodInfo info = new MethodInfo(cur.getName(), cur.getParameterTypes(), cur.getReturnType());
                    methodInfos.add(info);
                }
            }

        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    public String callMethod(String arguments, MethodInfo methodInfo){
        try {
            String[] args=arguments.split(" ");
            Object[] objects=new Object[methodInfo.getArguments().length];
           for(int i=0; i<methodInfo.getArguments().length; i++){
               objects[i]=args[i];
           }
            Class selectedClass=Class.forName(curClass);
            Object obj=selectedClass.newInstance();
            Method selectedMethod=selectedClass.getMethod(methodInfo.getName());
            Object returnObject=selectedMethod.invoke(obj, objects);
            if(returnObject==null) return "void";
            return returnObject.toString();
        }
        catch (ClassNotFoundException|NoSuchMethodException|InstantiationException|IllegalAccessException| InvocationTargetException e){

            //e.printStackTrace();
        }

        return "NoObject";
    }
}
