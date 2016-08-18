

package com.godoro.inventory.data.repository.impl;

import com.godoro.base.BaseRepository;
import com.godoro.inventory.data.entity.Product;
import com.godoro.inventory.data.repository.ProductDao;

import java.util.List;
import javax.persistence.Query;


public class ProductDaoImpl extends BaseRepository<Product, Long> implements ProductDao{

    public ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> getProductByCategoryId(long categoryId) {
        String jpql = createSelect();
        jpql += " where product.category.categoryId = :categoryId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("categoryId", categoryId);
          
        return query.getResultList();
    }

    @Override
    public int getCountByCategoryId(long categoryId) {
        String jpql = createCount();
        jpql += " where product.category.categoryId = :categoryId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("categoryId", categoryId);
        return ((Long) query.getSingleResult()).intValue();
    }

}
