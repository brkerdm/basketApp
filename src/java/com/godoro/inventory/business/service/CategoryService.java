/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.inventory.business.service;

import com.godoro.inventory.business.view.CategoryDetail;
import com.godoro.inventory.business.view.CategorySummary;

/**
 *
 * @author burak
 */
public interface CategoryService {
    
     public CategorySummary getParentCategories();
     
     public CategorySummary getChildCategories(long categoryId);
      
     public CategorySummary getCategoryByParent(long categoryId);
      
     public CategorySummary getNotInCategory(long categoryId);
     
     public void create(CategoryDetail categoryView);
     
     public void update(CategoryDetail categoryView);
     
     public void remove(long categoryId);
     
     public CategoryDetail get(long categoryId);
     
     public CategoryDetail getByReference(long categoryId);
     
     public CategorySummary getList();
     
     public CategorySummary getList(int start, int offset);
     
     public int getCount();
}
