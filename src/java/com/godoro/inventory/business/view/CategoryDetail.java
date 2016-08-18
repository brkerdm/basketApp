

package com.godoro.inventory.business.view;

import java.util.List;


public class CategoryDetail {
    
     private long categoryId;
     private String categoryName;
     private List<ProductDetail> productList;
     private List<CategoryDetail> childCategories;
     private long parentCategoryId;
     private String parentCategoryName;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ProductDetail> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDetail> productList) {
        this.productList = productList;
    }

    public List<CategoryDetail> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<CategoryDetail> childCategories) {
        this.childCategories = childCategories;
    }

    public long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }
     
     

}
