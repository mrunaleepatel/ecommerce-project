package com.ecom.ecommerce.repositoryimpl;

import com.ecom.ecommerce.entity.Category;
import com.ecom.ecommerce.repository.CategoryRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository("categoryRepository")
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category>list(){
        String selectActiveCategory = "FROM Category WHERE active = :active";
        Query query = (Query) sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
        query.setParameter("active", true);
        return query.getResultList();
    }

//    single category based on id
    @Override
    public Category get(int id){
        return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
    }

    @Override
    public boolean add(Category category){
        try {
//            add category to database table
            sessionFactory.getCurrentSession().persist(category);
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

//    update single category
    @Override
    public boolean update(Category category){
        try {
            sessionFactory.getCurrentSession().merge(category);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean delete(Category category){
        category.setActive(false);
        try {
            sessionFactory.getCurrentSession().merge(category);
        return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
