package service;

import util.UTF8Control;

import java.util.ResourceBundle;

public class ResourceDataReader {
    private static ResourceBundle environmentBundle = ResourceBundle.getBundle(System.getProperty("environment"), new UTF8Control());

    public static String getEnvironmentData(String key){
        return environmentBundle.getString(key);
    }
}