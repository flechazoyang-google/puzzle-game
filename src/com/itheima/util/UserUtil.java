package com.itheima.util;

import com.itheima.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    private static final String FILE_PATH = "data/users.txt";

    private UserUtil() {}

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return users;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    users.add(new User(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static boolean saveUser(User user) {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(user.getUsername() + "," + user.getPassword());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean userExists(String username) {
        for (User u : loadUsers()) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
