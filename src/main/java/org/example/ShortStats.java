package org.example;

public class ShortStats extends Stats {
    public ShortStats() {
        super();
    }

    @Override
    public void printStats() {
        System.out.println("SHORT STATS");
        System.out.println("    int amount = " + super.getCountInt());
        System.out.println("    float amount = " + super.getCountFloat());
        System.out.println("    strings amount = " + super.getCountStr());
    }

    @Override
    public Type addToStats(String line) {
        if (isInteger(line)) {
            super.setCountInt(super.getCountInt() + 1);
            return Type.INTEGER;
        } else if (isFloat(line)) {
            super.setCountFloat(super.getCountFloat() + 1);
            return Type.FLOAT;
        } else {
            super.setCountStr(super.getCountStr() + 1);
            return Type.STRING;
        }
    }


}
