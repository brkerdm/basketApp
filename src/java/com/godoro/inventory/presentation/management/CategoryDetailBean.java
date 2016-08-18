

package com.godoro.inventory.presentation.management;

import com.godoro.inventory.business.service.CategoryService;
import com.godoro.inventory.business.view.CategoryDetail;
import com.godoro.library.FacesUtilities;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CategoryDetailBean {
    
    private CategoryDetail categoryDetail;
    
    @Inject
    private CategoryService categoryService;
    
    @PostConstruct
    public void init() {
        long categoryId = FacesUtilities.getParameter("categoryId", 0);
        
        if (categoryId == 0) {
            categoryDetail = new CategoryDetail();
            categoryDetail.setCategoryName("");
        } else {
           categoryDetail = categoryService.get(categoryId); 
        }
    }
    

    public CategoryDetail getCategoryDetail() {
        return categoryDetail;
    }
    
    public void clearForm() {
        categoryDetail.setCategoryId(0);
        categoryDetail.setCategoryName(null);
        categoryDetail.setParentCategoryId(0);
        categoryDetail.setParentCategoryName(null);
    }
    
    public void save() {
        long categoryId = FacesUtilities.getParameter("categoryId", 0);
         if (categoryId == 0) {
            categoryService.create(categoryDetail);
        } else {
            categoryService.update(categoryDetail);
        }
         
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", "" ));
    }

}
