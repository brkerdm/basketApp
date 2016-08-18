

package com.godoro.inventory.business.service.impl;

import com.godoro.inventory.business.service.CategoryService;
import com.godoro.inventory.business.view.CategoryDetail;
import com.godoro.inventory.business.view.CategorySummary;
import com.godoro.inventory.data.entity.Category;
import com.godoro.inventory.data.repository.CategoryDao;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CategoryServiceImpl implements CategoryService{
    
    private CategoryDao categoryDao;
    
    @Inject
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
        
    }

    public CategoryServiceImpl() {
        
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }
    
    private CategorySummary mapListToView(List<Category> categoryList) {
        CategorySummary categorySummary = new CategorySummary();
        if (categoryList != null && !categoryList.isEmpty()) {
            categorySummary.setCategoryList(new ArrayList<CategoryDetail>());
            for (Category category : categoryList) {
                CategoryDetail categoryVO = new CategoryDetail();
                categoryVO.setCategoryId(category.getCategoryId());
                categoryVO.setCategoryName(category.getCategoryName());
                if (category.getParent() != null) {
                   categoryVO.setParentCategoryId(category.getParent().getCategoryId()); 
                   categoryVO.setParentCategoryName(category.getParent().getCategoryName());
                }
                
                categorySummary.getCategoryList().add(categoryVO);
            }
        }
        return categorySummary;
    }

    @Override
    public CategorySummary getParentCategories() {
        List<Category> categoryList = getCategoryDao().getParentCategories();
        return mapListToView(categoryList);
    }
    

    @Override
    public CategorySummary getChildCategories(long categoryId) {
       List<Category> categoryList = getCategoryDao().getChildCategories(categoryId);
       return mapListToView(categoryList);
    }

    @Override
    public CategorySummary getCategoryByParent(long categoryId) {
       List<Category> categoryList = getCategoryDao().getChildCategories(categoryId);
       return mapListToView(categoryList);
    }
    

    @Override
    public CategorySummary getNotInCategory(long categoryId) {
       List<Category> categoryList = getCategoryDao().getNotInCategory(categoryId);
       return mapListToView(categoryList);
    }

    @Override
    public void create(CategoryDetail categoryView) {
        Category category = new Category();
        category.setCategoryName(categoryView.getCategoryName());
        if (categoryView.getParentCategoryId() > 0) {
            category.setParent(getCategoryDao().findByReference(categoryView.getParentCategoryId()));
        }
        
        getCategoryDao().persist(category);
        
                
    }

    @Override
    public void update(CategoryDetail categoryView) {
        Category category = null;
        if (categoryView.getCategoryId() == 0) {
            //throw error
            return;
        }
        category = getCategoryDao().find(categoryView.getCategoryId());
        category.setCategoryName(categoryView.getCategoryName());
        if (categoryView.getParentCategoryId() > 0) {
            category.setParent(getCategoryDao().find(categoryView.getParentCategoryId()));
        } else {
            category.setParent(null);
        }
        
        getCategoryDao().merge(category);
        
    }

    @Override
    public void remove(long categoryId) {
        getCategoryDao().remove(categoryId);
    }

    @Override
    public CategoryDetail get(long categoryId) {
        Category category = getCategoryDao().find(categoryId);
        if (category == null) {
            //throw error
            return null;
        }
        CategoryDetail categoryDetail = new CategoryDetail();
        categoryDetail.setCategoryId(category.getCategoryId());
        categoryDetail.setCategoryName(category.getCategoryName());
        if (category.getParent() != null) {
            categoryDetail.setParentCategoryId(category.getParent().getCategoryId());
            categoryDetail.setParentCategoryName(category.getParent().getCategoryName());
        }
        
        return categoryDetail;
   
        
    }

    @Override
    public CategoryDetail getByReference(long categoryId) {
        Category category = getCategoryDao().findByReference(categoryId);
        if (category == null) {
            //throw error
            return null;
        }
        CategoryDetail categoryDetail = new CategoryDetail();
        categoryDetail.setCategoryId(category.getCategoryId());
        categoryDetail.setCategoryName(category.getCategoryName());
        if (category.getParent() != null) {
            categoryDetail.setParentCategoryId(category.getParent().getCategoryId());
            categoryDetail.setParentCategoryName(category.getParent().getCategoryName());
        }
        
        return categoryDetail;
    }

    @Override
    public CategorySummary getList() {
        List<Category> categoryList = getCategoryDao().getList();
        return mapListToView(categoryList);
    }

    @Override
    public CategorySummary getList(int start, int offset) {
        List<Category> categoryList = getCategoryDao().getList(start, offset);
        return mapListToView(categoryList);
    }

    @Override
    public int getCount() {
        return getCategoryDao().getCount();
    }
    
    
    
    

}
