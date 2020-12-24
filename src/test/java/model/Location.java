package model;

import java.util.Objects;

public class Location {
    private String country;
    private String city;
    private String region;
    private String address;
    private String postcode;

    public Location(String country,
                    String city,
                    String region,
                    String address,
                    String postcode) {
        this.country = country;
        this.city = city;
        this.region = region;
        this.address = address;
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(getAddress(), location.getAddress()) &&
                Objects.equals(getCity(), location.getCity()) &&
                Objects.equals(getCountry(), location.getCountry()) &&
                Objects.equals(getPostcode(), location.getPostcode()) &&
                Objects.equals(getRegion(), location.getRegion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress(), getCity(), getCountry(), getPostcode(), getRegion());
    }
}
