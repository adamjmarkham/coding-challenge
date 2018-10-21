package com.landbay.model.internal;

public class PropertyAddress {

    private String street;
    private int number;
    private String city;
    private String postCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof PropertyAddress)) { return false; }

        PropertyAddress that = (PropertyAddress) o;

        if (number != that.number) { return false; }
        if (street != null ? !street.equals(that.street) : that.street != null) { return false; }
        if (city != null ? !city.equals(that.city) : that.city != null) { return false; }
        return postCode != null ? postCode.equals(that.postCode) : that.postCode == null;
    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + number;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (postCode != null ? postCode.hashCode() : 0);
        return result;
    }
}
