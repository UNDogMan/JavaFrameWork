package model;

public enum ProductSize {

    XS("XS"),
    S("S"),
    M("M"),
    L("L"),
    XL("XL"),
    XXL("2XL"),
    XXXL("3XL");

    private final String size;

    ProductSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
