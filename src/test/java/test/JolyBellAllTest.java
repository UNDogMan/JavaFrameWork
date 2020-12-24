package test;

import model.ProductInfo;
import model.ProductSize;
import org.testng.annotations.Test;
import page.*;
import service.LocationCreator;
import service.ProductCreator;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JolyBellAllTest extends CommonConditions{
    @Test
    public void noValidPromoCodeMessageTest() {
        CartModal<ProductPage> cartModal = new ProductPage(driver)
                .clickAddToCartButton()
                .interPromoCode("Gold")
                .activatePromoCode();

        assertThat(cartModal.getLastNotification(),is(equalTo("Запрашиваемого промокода не существует или он закончился.")));
    }

    @Test
    public void removeProductFromCartTest() {
        ProductInfo tshirt = ProductCreator.tshirtProductInfo();

        List<ProductInfo> productInfoList = new SizedProductPage(driver)
                .clickAddToCartButton()
                .removeOrderedProduct(tshirt)
                .getInCartProductInfo();

        assertThat(productInfoList, not(contains(tshirt)));
    }

    @Test
    public void changeProductSizeInCartTest() {
        ProductInfo tshirt = ProductCreator.tshirtProductInfo();
        ProductInfo bigtshirt = ProductCreator.bigTshirtProductInfo();

        List<ProductInfo> productInfoList = new SizedProductPage(driver)
                .clickAddToCartButton()
                .changeOrderedProductSize(tshirt, ProductSize.XXXL)
                .getInCartProductInfo();

        assertThat(productInfoList, is(contains(bigtshirt)));
    }

    @Test
    public void increaseProductCountInCartTest() {
        ProductInfo tshirt = ProductCreator.tshirtProductInfo();
        ProductInfo tshirts = ProductCreator.tshirtsProductInfo();

        List<ProductInfo> productInfoList = new SizedProductPage(driver)
                .clickAddToCartButton()
                .increaseOrderedProduct(tshirt)
                .getInCartProductInfo();

        assertThat(productInfoList, is(contains(tshirts)));
    }

    @Test
    public void decreaseProductCountInCartBelowOneTest() {
        ProductInfo tshirt = ProductCreator.tshirtProductInfo();

        CartModal<ProductPage> cartModal = new SizedProductPage(driver)
                .clickAddToCartButton()
                .decreaseOrderedProduct(tshirt);

        assertThat(cartModal.getInCartProductInfo(), contains(tshirt));
        assertThat(cartModal.getLastNotification(), is(equalTo("Ожидаемое количество является недопустимым.")));
    }

    @Test
    public void differentPriceForDifferentSizesTest() {
        ProductInfo tshirt = new ProductInfo("B.O.M.J Black", "1", "2 300 RUB" , "XS" );
        ProductInfo bigtshirt = new ProductInfo("B.O.M.J Black", "1", "2 540 RUB" , "3XL" );

        SizedProductPage sizedProductPage = (SizedProductPage) new SizedProductPage(driver).clickAddToCartButton().continueShopping();
        List<ProductInfo> productInfoList = sizedProductPage.setSize(ProductSize.XXXL).clickAddToCartButton().getInCartProductInfo();

        assertThat(productInfoList.get(0).getPrice(), not(equalTo(productInfoList.get(1).getPrice())));
    }

    @Test
    public void differentPriceForDifferentRegionsTest() {
        new SizedProductPage(driver)
                .clickAddToCartButton()
                .checkoutCart();

        BookingPage bookingPage = new BookingPage(driver).enterLocation(LocationCreator.belarusLocation());

        String oldPrice = bookingPage.getTotalOrderPrice();
        String newPrice = bookingPage
                .enterLocation(LocationCreator.ukraineLocation())
                .getTotalOrderPrice();

        assertThat(oldPrice, not(equalTo(newPrice)));
    }

    @Test
    public void voidCartCheckoutTest() {
        CartModal<MainPage> cart = new MainPage(driver).openCart();
        assertThat(cart.checkoutCart(), is(false));
        assertThat(cart.getLastNotification(),
                is(equalTo("Вы не можете перейти к оформлению заказа т.к. корзина пуста.")));
    }
}
