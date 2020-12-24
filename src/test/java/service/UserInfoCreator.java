package service;

import model.UserInfo;

public class UserInfoCreator {
    public static final String TEST_DATA_FIRST_USERINFO = "userinfo.first.";
    public static final String TEST_DATA_SECOND_USERINFO = "userinfo.second.";
    public static final String TEST_DATA_NAME = "name";
    public static final String TEST_DATA_SURNAME = "surname";
    public static final String TEST_DATA_PATRONYMIC = "patronymic";
    public static final String TEST_DATA_PHONENUMBER = "phoneNumber";
    public static final String TEST_DATA_EMAIL = "email";

    private static UserInfo withCredentialsFromProperty(String userInfo){
        return new UserInfo(ResourceDataReader.getEnvironmentData(userInfo + TEST_DATA_NAME),
                ResourceDataReader.getEnvironmentData(userInfo + TEST_DATA_SURNAME),
                ResourceDataReader.getEnvironmentData(userInfo + TEST_DATA_PATRONYMIC),
                ResourceDataReader.getEnvironmentData(userInfo + TEST_DATA_PHONENUMBER),
                ResourceDataReader.getEnvironmentData(userInfo + TEST_DATA_EMAIL));
    }

    public static UserInfo firstUserInfo() {
        return withCredentialsFromProperty(TEST_DATA_FIRST_USERINFO);
    }

    public static UserInfo secondUserInfo() {
        return withCredentialsFromProperty(TEST_DATA_SECOND_USERINFO);
    }

}
