package model;

import java.util.Objects;

public class UserInfo {
    private String name;
    private String surname;
    private String patronymic;
    private String phoneNumber;
    private String email;

    public UserInfo(String name,
                    String surname,
                    String patronymic,
                    String phoneNumber,
                    String email) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(getName(), userInfo.getName()) &&
                Objects.equals(getSurname(), userInfo.getSurname()) &&
                Objects.equals(getPatronymic(), userInfo.getPatronymic()) &&
                Objects.equals(getPhoneNumber(), userInfo.getPhoneNumber()) &&
                Objects.equals(getEmail(), userInfo.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getPatronymic(), getPhoneNumber(), getEmail());
    }
}
