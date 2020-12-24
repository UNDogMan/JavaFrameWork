package service;

import model.User;

public class UserCreator {
    public static final String TESTDATA_USER_EMAIL = "user.email";
    public static final String TESTDATA_USER_PASSWORD = "user.password";

    public static User withCredentialsFromProperty(){
        return new User(ResourceDataReader.getEnvironmentData(TESTDATA_USER_EMAIL),
                ResourceDataReader.getEnvironmentData(TESTDATA_USER_PASSWORD));
    }

    public static User withEmptyEmail(){
        return new User("", ResourceDataReader.getEnvironmentData(TESTDATA_USER_PASSWORD));
    }

    public static User withEmptyPassword(){
        return new User(ResourceDataReader.getEnvironmentData(TESTDATA_USER_EMAIL), "");
    }
}
