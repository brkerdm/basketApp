

package com.godoro.inventory.presentation.portal;


import com.godoro.inventory.business.service.ProductService;
import com.godoro.inventory.business.view.ProductDetail;
import com.godoro.inventory.business.view.ProductSummary;
import com.godoro.library.FacesUtilities;
import com.godoro.library.RepeatPaginator;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ProductListBean implements Serializable {
    
    private ProductSummary productSummary;
    private ProductDetail selectedProduct;
    private RepeatPaginator paginator;

    public ProductDetail getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductDetail selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    
    @Inject
    transient private ProductService productService;
    
    @PostConstruct
    public void init() {
        long categoryId = FacesUtilities.getParameter("categoryId", 0);
        
        if (categoryId == 0) {
            productSummary = productService.getList();
        } else {
            productSummary = productService.getProductByCategoryId(categoryId);
        }
        
        if (productSummary.getProductList() != null) {
             paginator = new RepeatPaginator(productSummary.getProductList());
        } else {
            paginator = new RepeatPaginator(new ArrayList<>());
        }
       
    }

    public RepeatPaginator getPaginator() {
        return paginator;
    }

    public ProductSummary getProductSummary() {
        return productSummary;
    }
    
    
}
