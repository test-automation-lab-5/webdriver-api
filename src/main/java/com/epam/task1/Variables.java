package com.epam.task1;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface Variables {
      String URL = "https://accounts.google.com/signin";
      String DRIVER_URL = "src/main/resources/chromedriver.exe";
      String LOGIN = "jamesdaw11101993@gmail.com";
      String PASSWORD = "7483145okokokokok";
      String RECEIVER = "jamesdaw11101993@gmail.com";
      String SUBJECT = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      String MESSAGE = "test message";

}
