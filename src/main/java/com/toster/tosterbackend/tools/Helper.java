package com.toster.tosterbackend.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {

    public static String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}
