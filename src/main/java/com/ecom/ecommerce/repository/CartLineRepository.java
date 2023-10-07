package com.ecom.ecommerce.repository;

import com.ecom.ecommerce.entity.Cart;
import com.ecom.ecommerce.entity.CartLine;
import com.ecom.ecommerce.entity.OrderDetail;

import java.util.List;

public interface CartLineRepository {
    List<CartLine>list(int cartId);
    CartLine get(int id);
    boolean add(CartLine cartLine);
    boolean update(CartLine cartLine);
    boolean remove(CartLine cartLine);

//    fetch cartline based on cartid and productid
    CartLine getByCartandProduct(int cartId, int productId);
    boolean updateCart(Cart cart);
    List<CartLine>listAvailable(int cartId);

//    add order details
    boolean addOrderDetail(OrderDetail orderDetail);


}
