package com.ecom.ecommerce.repository;

import com.ecom.ecommerce.entity.Address;
import com.ecom.ecommerce.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository {
//    user operation
    User getByEmail(String email);
    User get(int id);
    boolean add(User user);
//    adding, updating address
    boolean addAddress(Address address);
    boolean updateAddress(Address address);
    Address getBillingAddress(int userId);
    List<Address>listShippingAddresses(int userId);

    Address getAddress(int addressId);
}
