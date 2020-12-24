package model;

import java.util.Objects;

public class ProductInfo {
    private String name;
    private String count;
    private String price;
    private String size;

    public ProductInfo(String name, String count, String price, String size) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.size = size;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", count='" + count + '\'' +
                ", price='" + price + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof ProductInfo)) return false;
        ProductInfo productInfo = (ProductInfo) o;
        return Objects.equals(getName(), productInfo.getName()) &&
                Objects.equals(getCount(), productInfo.getCount()) &&
                Objects.equals(getPrice(), productInfo.getPrice()) &&
                Objects.equals(getSize(), productInfo.getSize());
    }
}
