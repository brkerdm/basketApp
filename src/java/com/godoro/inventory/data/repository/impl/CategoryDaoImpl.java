

package com.godoro.inventory.data.repository.impl;

import com.godoro.base.BaseRepository;
import com.godoro.inventory.data.entity.Category;
import com.godoro.inventory.data.repository.CategoryDao;
import java.util.List;
import javax.persistence.Query;


public class CategoryDaoImpl extends BaseRepository<Category, Long> implements CategoryDao{

   
    public CategoryDaoImpl() {
        super(Category.class);
    }
    
    @Override
    public List<Category> getParentCategories() {
        String jpql = createSelect();
        jpql += " where category.parent IS NULL";
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    @Override
    public List<Category> getChildCategories(long categoryId) {
       String jpql = createSelect();
       jpql += " where category.parent.categoryId = :categoryId";
       Query query = entityManager.createQuery(jpql);
       query.setParameter("categoryId", categoryId);
       return query.getResultList();
    }

    @Override
    public List<Category> getCategoryByParent(long categoryId) {
        String jpql = createSelect();
        jpql += " where category.parent.categoryId = :categoryId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("categoryId", categoryId);
        
        return query.getResultList();
    }

    @Override
    public List<Category> getNotInCategory(long categoryId) {
         String nativeQuery = "SELECT * FROM category as cat " +
                             "WHERE cat.cat_id != ?1 and (cat.parent is null or " +
                             "cat.cat_id not IN " +
                             "(" +
                             "select @pv:=cat_id as category_id from category " +
                             "join " +
                             "(select @pv:=?2)tmp " +
                             "where parent=@pv " +
                            ") )";
        Query query = entityManager.createNativeQuery(nativeQuery, Category.class);
        query.setParameter(1, categoryId);
        query.setParameter(2, categoryId);
        
        return query.getResultList();
    }

}
