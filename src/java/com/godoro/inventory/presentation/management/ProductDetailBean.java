

package com.godoro.inventory.presentation.management;

import com.godoro.inventory.business.service.ProductService;
import com.godoro.inventory.business.view.ProductDetail;
import com.godoro.library.FacesUtilities;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ProductDetailBean {
    private ProductDetail productDetail;
   
    
    @Inject
    private ProductService productService;
    
    @PostConstruct
    public void init() {
        long productId = FacesUtilities.getParameter("productId", 0);
         if (productId == 0) {
            productDetail = new ProductDetail();
            productDetail.setProductName("");
        } else {
            productDetail = productService.get(productId);
            
        }
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    
     public void save() {
         long productId = FacesUtilities.getParameter("productId", 0);
          if (productId == 0) {
            productService.create(productDetail);
        } else {
            productService.update(productDetail);
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", "" ));
     }
    
    public void clearForm() {
        productDetail.setProductId(0);
        productDetail.setProductName(null);
        productDetail.setSalesPrice(0.0);
        productDetail.setCategoryId(0);
        productDetail.setCategoryName(null);
    }
}
