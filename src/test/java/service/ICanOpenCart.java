package service;

import page.AbstractPage;
import page.CartModal;

public interface ICanOpenCart<T extends AbstractPage> {
    public CartModal<T> openCart();
}
