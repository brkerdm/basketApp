

package com.godoro.inventory.presentation.portal;

import com.godoro.inventory.business.service.CategoryService;
import com.godoro.inventory.business.view.CategoryDetail;
import com.godoro.library.FacesUtilities;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CategoryViewBean {
    private CategoryDetail categoryDetail;
    
    @Inject
    private CategoryService categoryService;

    public CategoryDetail getCategoryDetail() {
        return categoryDetail;
    }
    
    @PostConstruct
    public void init() {
        long categoryId = FacesUtilities.getParameter("categoryId", 0);
        if (categoryId == 0) {
            categoryDetail = new CategoryDetail();
            categoryDetail.setCategoryName("");
            categoryDetail.setParentCategoryName("");
        } else {
            categoryDetail = categoryService.get(categoryId);
        }
    }
    
    
}
