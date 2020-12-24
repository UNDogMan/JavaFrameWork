package service;

import model.ProductInfo;

public class ProductCreator {
    public static final String TEST_DATA_TSHIRT_PRODUCT = "product.tshirt.";
    public static final String TEST_DATA_TSHIRTS_PRODUCT = "product.tshirts.";
    public static final String TEST_DATA_BIGTSHIRT_PRODUCT = "product.bigtshirt.";
    public static final String TEST_DATA_NAME = "name";
    public static final String TEST_DATA_COUNT = "count";
    public static final String TEST_DATA_PRICE = "price";
    public static final String TEST_DATA_SIZE = "size";

    private static ProductInfo withCredentialsFromProperty(String product){
        return new ProductInfo(ResourceDataReader.getEnvironmentData(product + TEST_DATA_NAME),
                ResourceDataReader.getEnvironmentData(product + TEST_DATA_COUNT),
                ResourceDataReader.getEnvironmentData(product + TEST_DATA_PRICE),
                ResourceDataReader.getEnvironmentData(product + TEST_DATA_SIZE));
    }

    public static ProductInfo tshirtProductInfo() {
        return withCredentialsFromProperty(TEST_DATA_TSHIRT_PRODUCT);
    }

    public static ProductInfo tshirtsProductInfo() {
        return withCredentialsFromProperty(TEST_DATA_TSHIRTS_PRODUCT);
    }

    public static ProductInfo bigTshirtProductInfo() {
        return withCredentialsFromProperty(TEST_DATA_BIGTSHIRT_PRODUCT);
    }
}
