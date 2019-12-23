package by.bsu.mss2019.kazlova.dstask3;

public class ClassInfo {
    protected String name;

    public ClassInfo(){}


    public ClassInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
