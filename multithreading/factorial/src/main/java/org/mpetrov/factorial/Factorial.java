package org.mpetrov.factorial;

import java.math.BigInteger;

public class Factorial implements Runnable {
    private int parameter;

    public Factorial(int parameter) {
        this.parameter = parameter;
    }

    public void run() {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= parameter; i++)
            result = result.multiply(BigInteger.valueOf(i));
        System.out.println(parameter + "! = " + result);
    }

}