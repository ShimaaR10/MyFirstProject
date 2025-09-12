package com.shimaa.frameworkpractice.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties props;

    // Load file once
    public static void loadConfig() throws IOException {
        if (props == null) {
           FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            props = new Properties();
            props.load(fis);
        }
    }

    // Getter method
    public static String get(String key) {
        return props.getProperty(key);
    }

}