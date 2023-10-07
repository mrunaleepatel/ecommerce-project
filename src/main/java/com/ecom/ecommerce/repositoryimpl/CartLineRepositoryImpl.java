package com.ecom.ecommerce.repositoryimpl;

import com.ecom.ecommerce.entity.Cart;
import com.ecom.ecommerce.entity.CartLine;
import com.ecom.ecommerce.entity.OrderDetail;
import com.ecom.ecommerce.repository.CartLineRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("cartLineRepository")
@Transactional
public class CartLineRepositoryImpl implements CartLineRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public CartLineRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CartLine getByCartandProduct(int cartId, int productId){
        String query = "FROM CartLine WHERE cartId = :cartId AND product.id = :productId";
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery(query, CartLine.class)
                    .setParameter("cartId", cartId)
                    .setParameter("productId", productId)
                    .getSingleResult();
        } catch (Exception ex){
            return null;
        }
    }
    @Override
    public boolean add(CartLine cartLine){
        try {
            sessionFactory.getCurrentSession()
                    .persist(cartLine);
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean update(CartLine cartLine) {
        try {
            sessionFactory.getCurrentSession().merge(cartLine);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean remove(CartLine cartLine){
        try {
            sessionFactory.getCurrentSession().remove(cartLine);
            return true;
        } catch (Exception ex){
            return false;
        }
    }
    @Override
    public List<CartLine>list(int cartId){
        String query = "FROM CartLine WHERE cartId = :cartId";
        return sessionFactory.getCurrentSession()
                .createQuery(query, CartLine.class)
                .setParameter("cartId", cartId)
                .getResultList();
    }
    @Override
    public CartLine get(int id) {
        return sessionFactory.getCurrentSession().get(CartLine.class, Integer.valueOf(id));
    }
    @Override
    public boolean updateCart(Cart cart) {
        try {
            sessionFactory.getCurrentSession().update(cart);
            return true;
        }
        catch(Exception ex) {
            return false;
        }
    }
    @Override
    public List<CartLine> listAvailable(int cartId) {
        String query = "FROM CartLine WHERE cartId = :cartId AND available = :available";
        return sessionFactory.getCurrentSession()
                .createQuery(query, CartLine.class)
                .setParameter("cartId", cartId)
                .setParameter("available", true)
                .getResultList();
    }
    @Override
    public boolean addOrderDetail(OrderDetail orderDetail) {
        try {
            sessionFactory.getCurrentSession().persist(orderDetail);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
}
