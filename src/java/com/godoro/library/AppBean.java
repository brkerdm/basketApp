

package com.godoro.library;

import com.godoro.inventory.business.service.CategoryService;
import com.godoro.inventory.business.view.CategoryDetail;
import com.godoro.inventory.business.view.CategorySummary;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AppBean {
    
    @Inject
    private CategoryService categoryService;
    
    public List<CategoryDetail> getCategoryList() {
        CategorySummary categorySummary = categoryService.getList();
        if (categorySummary != null) {
            return categorySummary.getCategoryList();
        }
        return new ArrayList<CategoryDetail>();
        
    }
    
    //get categories that are not children of the category with categoryId
    public List<CategoryDetail> getCategoryList2(long categoryId) {
        CategorySummary categorySummary = categoryService.getNotInCategory(categoryId);
        if (categorySummary != null) {
            return categorySummary.getCategoryList();
        }
        return new ArrayList<CategoryDetail>();
    }
}
