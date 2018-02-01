package com.epam.lab;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GlobalConfiguration {
    public static String TARGET_MAIL;
    public static String INCORRECT_MAIL;
    public static String SUBJECT;
    public static String MESSAGE;
    public static String URL;
    public static String MAIL;
    public static String PASSWORD;
    public static String NAME_DRIVER;
    public static String PATH_DRIVER;

    public static void loadData() {
        List<String> data = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src//main//resources//TestData.dat"));
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            reader.close();
            TARGET_MAIL = data.get(0);
            INCORRECT_MAIL = data.get(1);
            SUBJECT = data.get(2);
            MESSAGE = data.get(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("src//main//resources//config.properties");
            prop.load(input);
            URL = prop.getProperty("URL");
            MAIL = prop.getProperty("MAIL");
            PASSWORD = prop.getProperty("PASSWORD");
            NAME_DRIVER = prop.getProperty("NAME_DRIVER");
            PATH_DRIVER = prop.getProperty("PATH_DRIVER");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



