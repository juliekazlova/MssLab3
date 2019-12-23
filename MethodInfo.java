package by.bsu.mss2019.kazlova.dstask3;

import java.lang.reflect.Type;
import java.util.Arrays;

public class MethodInfo extends ClassInfo {
    //private String name;
    private Type[] arguments;
    private Type returns;

    public MethodInfo(String name, Type[] arguments, Type returns) {
        this.name = name;
        this.arguments = arguments;
        this.returns = returns;
    }

    public String getName() {
        return name;
    }

    public Type[] getArguments() {
        return arguments;
    }

    public Type getReturns() {
        return returns;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(Type arg: arguments){
            sb.append(arg.getTypeName()).append(", ");
        }
        return name+", returns "+returns.getTypeName();
    }
}
