/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.inventory.data.repository;

import com.godoro.base.GenericRepository;
import com.godoro.inventory.data.entity.Category;
import java.util.List;

/**
 *
 * @author burak
 */
public interface CategoryDao extends GenericRepository<Category, Long>{
    
     public List<Category> getParentCategories();
     
     public List<Category> getChildCategories(long categoryId);
      
     public List<Category> getCategoryByParent(long categoryId);
      
     public List<Category> getNotInCategory(long categoryId);
    
}
