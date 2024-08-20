package org.example;

public abstract class Stats {
    private int countInt,
            countFloat,
            countStr;

    public Stats() {
        this.countInt = 0;
        this.countFloat = 0;
        this.countStr = 0;
    }

    public int getCountInt() {
        return countInt;
    }

    public void setCountInt(int countInt) {
        this.countInt = countInt;
    }

    public void setCountStr(int countStr) {
        this.countStr = countStr;
    }

    public void setCountFloat(int countFloat) {
        this.countFloat = countFloat;
    }

    public int getCountStr() {
        return countStr;
    }

    public int getCountFloat() {
        return countFloat;
    }

    public abstract void printStats();
    public abstract Type addToStats(String line);

    protected static boolean isInteger(String str) {
        return str.matches("-?\\d+");
    }

    protected static boolean isFloat(String str) {
        return str.matches("-?\\d+(\\.\\d+)?(E-\\d+)?");
    }

    public boolean isEmpty() {
        return countInt == 0 && countFloat == 0 && countStr == 0;
    }
}
