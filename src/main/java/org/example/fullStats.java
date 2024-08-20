package org.example;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class fullStats extends Stats {
    private BigDecimal minFloat, maxFloat,
            sumFloat;

    private BigInteger minInt, maxInt,
            sumInt;


    private int longestStr, shortestStr;

    public fullStats() {
        super();
        sumInt = BigInteger.valueOf(0);
        sumFloat = BigDecimal.valueOf(0);
        longestStr = -1;
        shortestStr = -1;
    }

    @Override
    public void printStats() {
        System.out.println("FULL STATS");
        System.out.println("For integers:");
        if (super.getCountInt() != 0) {
            System.out.println("    int amount = " + super.getCountInt());
            System.out.println("    max int = " + maxInt);
            System.out.println("    min int = " + minInt);
            System.out.println("    sum int = " + sumInt);
            System.out.println("    average int = "
                    + new BigDecimal(sumInt).divide(new BigDecimal(BigInteger.valueOf(super.getCountInt())), MathContext.DECIMAL128));
        } else {
            System.out.println("    No integers found");
        }

        System.out.println("For floats:");
        if (super.getCountFloat() != 0) {
            System.out.println("    float amount = " + super.getCountFloat());
            System.out.println("    max float = " + maxFloat);
            System.out.println("    min float = " + minFloat);
            System.out.println("    sum float = " + sumFloat);
            System.out.println("    average float = "
                    + sumFloat.divide(new BigDecimal(BigInteger.valueOf(super.getCountFloat())), MathContext.DECIMAL128));
        } else {
            System.out.println("    No floats found");
        }

        System.out.println("For strings:");
        if (super.getCountStr() != 0) {
            System.out.println("    strings amount = " + super.getCountStr());
            System.out.println("    length of the longest string = " + longestStr);
            System.out.println("    length of the shortest string = " + shortestStr);
        } else {
            System.out.println("    No floats found");
        }
    }

    @Override
    public Type addToStats(String line) {
        if (isInteger(line)) {
            if (super.getCountInt() == 0) {
                init_Int(line);
            }
            super.setCountInt(super.getCountInt() + 1);
            changeStats(new BigInteger(line));
            return Type.INTEGER;
        } else if (isFloat(line)) {
            if (super.getCountFloat() == 0) {
                init_Float(line);
            }
            super.setCountFloat(super.getCountFloat() + 1);
            changeStats(new BigDecimal(line));
            return Type.FLOAT;
        } else {
            if (super.getCountStr() == 0) {
                init_String(line);
            }
            super.setCountStr(super.getCountStr() + 1);
            changeStats(line);
            return Type.STRING;
        }
    }

    private void changeStats(BigInteger num) {
        if (num.compareTo(maxInt) > 0) {
            maxInt = num;
        }
        if (num.compareTo(maxInt) < 0) {
            minInt = num;
        }
        sumInt = sumInt.add(num);
    }

    private void changeStats(BigDecimal num) {
        if (num.compareTo(maxFloat) > 0) {
            maxFloat = num;
        }
        if (num.compareTo(maxFloat) < 0) {
            minFloat = num;
        }
        sumFloat = sumFloat.add(num);

    }

    private void changeStats(String str) {
        if (str.length() > longestStr) {
            longestStr = str.length();
        }
        if (str.length() < shortestStr) {
            shortestStr = str.length();
        }

    }

    private void init_Int(String line) {
        if (minInt == null) {
            minInt = new BigInteger(line);
            maxInt = new BigInteger(line);
        }
    }

    private void init_Float(String line) {
        if (minFloat == null) {
            minFloat = new BigDecimal(line);
            maxFloat = new BigDecimal(line);
        }
    }

    private void init_String(String line) {
        if (shortestStr == -1) {
            shortestStr = line.length();
            longestStr = line.length();
        }
    }
}
