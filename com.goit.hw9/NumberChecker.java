package com.goit.hw9;

import java.io.*;

public class NumberChecker {

    private  static final String PATH = "D:\\GoIT\\HW\\src\\main\\resources\\file.txt";

    public static void fileReader(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH))) {
            String numbers = bufferedReader.readLine();
            while (numbers != null) {
                if (numberChecker(numbers)) {
                    System.out.println(numbers);
                }
                numbers = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void existingCheck (File file){
        if (!file.exists()) {
            file.getParentFile().mkdirs();

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean numberChecker (String numbers){
        String format1 = "[(]\\d{3}[)][ ]\\d{3}[-]\\d{4}";
        String format2 = "\\d{3}[ ]\\d{3}[ ]\\d{4}";
        return numbers.matches(format1) || numbers.matches(format2);
    }

    public static void main(String[] args) throws IOException {
        File file = new File(PATH);
        existingCheck(file);
        fileReader(file);
    }
}
