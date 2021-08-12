package com.goit.hw9;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class JsonConverter {

    private static final String GSON_PATH = "D:\\GoIT\\HW\\src\\main\\resources\\file2.txt";
    private static final String JSON_PATH = "D:\\GoIT\\HW\\src\\main\\resources\\user.json";

    public static void fileReader(File file, List<User> users) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(GSON_PATH))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] text = line.split(" ");
                if (text.length == 2 && !text[0].equals("name") && !text[1].equals("age")) {
                    users.add(new User(text[0], Integer.parseInt(text[1])));
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileWriter(File file, List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_PATH))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String JSON = gson.toJson(users);
            writer.write(JSON);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void existingCheck(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        File GSON = new File(GSON_PATH);
        File JSON = new File(JSON_PATH);
        existingCheck(GSON);
        existingCheck(JSON);
        List<User> users = new LinkedList<>();
        fileReader(GSON, users);
        fileWriter(JSON, users);
    }
}

    class User {

        private final String name;
        private final int age;

        User (String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

