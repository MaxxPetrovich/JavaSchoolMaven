package org.mpetrov.factorial;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        List<Integer> inputList = new ArrayList<>();
        try (FileReader reader = new FileReader("C:\\Intel\\input.txt")) {
            int c;
            String num = "";
            while ((c = reader.read()) != -1) {
                if (c != ';') {
                    num += (char) c;
                } else {
                    inputList.add(Integer.parseInt(num));
                    num = "";
                }
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        for (int i = 0; i < inputList.size(); i++) {
            new Thread(new Factorial(inputList.get(i)), "MyThread" + i).start();
        }

    }

}
