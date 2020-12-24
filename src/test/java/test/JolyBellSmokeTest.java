package test;

import model.ProductInfo;
import model.ProductSize;
import org.testng.annotations.Test;
import page.*;
import service.LocationCreator;
import service.ProductCreator;
import service.UserCreator;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JolyBellSmokeTest extends CommonConditions {
    @Test
    void logInTest() {
        assertThat(new MainPage(driver).logIn(UserCreator.withCredentialsFromProperty()).isLoggedIn(), is(true));
    }

    @Test
    public void buyTShirtWithRecommendedProductTest() {
        ProductInfo tshirt = new ProductInfo("B.O.M.J Black", "1", "2 300 RUB" , "S" );
        ProductInfo cup = new ProductInfo("Термочашка", "1", "1 500 RUB" , null);

        SizedProductPage tshirtPage = new SizedProductPage(driver);
        tshirtPage =  (SizedProductPage)tshirtPage.setSize(ProductSize.S)
                .clickAddToCartButton()
                .continueShopping();
        CartModal<RecommendedProductModal> cartModal = tshirtPage.selectFirstRecommendedProduct()
                .addProductToCart();
        List<ProductInfo> productInfoList = cartModal.getInCartProductInfo();

        assertThat(productInfoList, contains(tshirt, cup));
    }
}
