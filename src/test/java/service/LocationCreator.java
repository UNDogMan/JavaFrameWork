package service;

import model.Location;

public class LocationCreator {
    public static final String TEST_DATA_BY_LOCATION = "location.by.";
    public static final String TEST_DATA_UA_LOCATION = "location.ua.";
    public static final String TEST_DATA_COUNTRY = "country";
    public static final String TEST_DATA_CITY = "city";
    public static final String TEST_DATA_REGION = "region";
    public static final String TEST_DATA_ADDRESS = "address";
    public static final String TEST_DATA_POSTCODE = "postcode";

    private static Location withCredentialsFromProperty(String countryProperty){
        return new Location(ResourceDataReader.getEnvironmentData(countryProperty + TEST_DATA_COUNTRY),
                ResourceDataReader.getEnvironmentData(countryProperty + TEST_DATA_CITY),
                ResourceDataReader.getEnvironmentData(countryProperty + TEST_DATA_REGION),
                ResourceDataReader.getEnvironmentData(countryProperty + TEST_DATA_ADDRESS),
                ResourceDataReader.getEnvironmentData(countryProperty + TEST_DATA_POSTCODE));
    }

    public static Location belarusLocation() {
        return withCredentialsFromProperty(TEST_DATA_BY_LOCATION);
    }

    public static Location ukraineLocation() {
        return withCredentialsFromProperty(TEST_DATA_UA_LOCATION);
    }

    public static Location withEmptyCredentials() {
        return new Location("", "", "", "", "");
    }
}
