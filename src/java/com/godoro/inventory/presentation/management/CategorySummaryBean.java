

package com.godoro.inventory.presentation.management;

import com.godoro.inventory.business.service.CategoryService;
import com.godoro.inventory.business.view.CategoryDetail;
import com.godoro.inventory.business.view.CategorySummary;
import com.godoro.library.FacesUtilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@Named
@ViewScoped
public class CategorySummaryBean implements Serializable{
    
    private LazyDataModel<CategoryDetail> lazyModel;
    private CategoryDetail selectedCategory;

    public CategoryDetail getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(CategoryDetail selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public LazyDataModel<CategoryDetail> getLazyModel() {
        return lazyModel;
    }
    
    private int pageSize = 5;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }
    
    @Inject
    transient private CategoryService categoryService;
    
    @PostConstruct
    public void init() {
        lazyModel = new LazyDataModel<CategoryDetail>() {
            @Override
            public List<CategoryDetail> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                CategorySummary categorySummary = null;
                 
                try {
                     categorySummary = categoryService.getList(first, pageSize);
                     if (categorySummary.getCategoryList() == null) {
                         categorySummary.setCategoryList(new ArrayList<CategoryDetail>());
                     }
                    
                 } catch (Exception ex) {
                     ex.printStackTrace();
                 }
                
                return categorySummary.getCategoryList();
            }
        };
        
         /** 
            * In a real application, this number should be resolved by a projection query 
            */
         lazyModel.setRowCount(categoryService.getCount());
         lazyModel.setPageSize(pageSize);
    }
    
    public void delete() {
        long categoryId = FacesUtilities.getParameter("categoryId", 0);
        categoryService.remove(categoryId);
    }
    
   public void deleteRow(CategoryDetail categoryDetail) {
        categoryService.remove(categoryDetail.getCategoryId());
   }
   
   public void cancel() {
       selectedCategory = new CategoryDetail();
   }
}
