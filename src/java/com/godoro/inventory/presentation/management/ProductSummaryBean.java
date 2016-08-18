

package com.godoro.inventory.presentation.management;

import com.godoro.inventory.business.service.ProductService;
import com.godoro.inventory.business.view.ProductDetail;
import com.godoro.inventory.business.view.ProductSummary;
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
public class ProductSummaryBean implements Serializable {
    
    private LazyDataModel<ProductDetail> lazyModel;
    private ProductDetail selectedProduct;
    private long categoryId;

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getCategoryId() {
        return categoryId;
    }
    
    @PostConstruct
    public void init() {
         loadLazyModel();
    }
    
    @Inject
    transient private ProductService productService;

    public LazyDataModel<ProductDetail> getLazyModel() {
        return lazyModel;
    }

    public ProductDetail getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductDetail selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    
    private int pageSize = 5;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }
    
    private void loadLazyModel() {
        lazyModel = new LazyDataModel<ProductDetail>() {
            @Override
            public List<ProductDetail> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                ProductSummary productSummary = new ProductSummary();
            
                 
                try {
                    if(categoryId == 0) {
                       productSummary = productService.getList(first, pageSize); 
                    } else {
                       productSummary = productService.getProductByCategoryId(categoryId);
                    }
                    
                    if (productSummary.getProductList() == null) {
                        productSummary.setProductList(new ArrayList<ProductDetail>());
                    }
                     
                    
                 } catch (Exception ex) {
                     ex.printStackTrace();
                 }
                
                return productSummary.getProductList();
            }
       
         };
        
            /** 
            * In a real application, this number should be resolved by a projection query 
            */
          if (categoryId == 0) {
              lazyModel.setRowCount(productService.getCount());
          } else {
              lazyModel.setRowCount(productService.getCountByCategoryId(categoryId));
          }
          
          lazyModel.setPageSize(pageSize);
    }
    
     public void filter() {
        loadLazyModel();
    }
     
    public void delete() { 
        long productId = FacesUtilities.getParameter("productId", 0);
        productService.remove(productId);
        
    }
    
    public void deleteRow(ProductDetail productDetail) {
        productService.remove(productDetail.getProductId());
    }
     
    public void cancel() {
        selectedProduct = new ProductDetail();
    }
     

}
