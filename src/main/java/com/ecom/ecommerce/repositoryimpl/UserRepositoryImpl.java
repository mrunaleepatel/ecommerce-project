package com.ecom.ecommerce.repositoryimpl;

import com.ecom.ecommerce.entity.Address;
import com.ecom.ecommerce.entity.User;
import com.ecom.ecommerce.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("UserRepository")
@Transactional
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getByEmail(String email) {
        String selectQuery = "FROM User WHERE email = :email";
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery(selectQuery, User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean add(User user) {
        try {
            sessionFactory.getCurrentSession().persist(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean addAddress(Address address) {
        try {
            sessionFactory.getCurrentSession().persist(address);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
public boolean updateAddress(Address address) {
  try {
      sessionFactory.getCurrentSession().merge(address);
    return true;
  } catch (Exception ex) {
    return false;
  }
}


    @Override
    public List<Address> listShippingAddresses(int userId) {
        String selectQuery = "FROM Address WHERE user_id = :userId AND shipping = :isShipping ORDER BY id DESC";
        return sessionFactory.getCurrentSession()
                .createQuery(selectQuery, Address.class)
                .setParameter("userId", userId)
                .setParameter("isShipping", true)
                .getResultList();
    }

    @Override
    public Address getBillingAddress(int userId) {
        String selectQuery = "FROM Address WHERE user_id = :userId AND billing = :isBilling";
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery(selectQuery, Address.class)
                    .setParameter("userId", userId)
                    .setParameter("isBilling", true)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public User get(int id){
        try {
            return sessionFactory.getCurrentSession().get(User.class, id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Address getAddress(int addressId){
        try {
            return sessionFactory.getCurrentSession().get(Address.class, addressId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
