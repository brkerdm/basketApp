

package com.godoro.inventory.presentation.portal;

import com.godoro.inventory.business.service.ProductService;
import com.godoro.inventory.business.view.ProductDetail;
import com.godoro.library.FacesUtilities;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ProductViewBean {
    
    private ProductDetail productDetail;
    
    @Inject
    private ProductService productService;

    public ProductDetail getProductDetail() {
        return productDetail;
    }
    
    @PostConstruct
    public void init() {
        long productId = FacesUtilities.getParameter("productId", 0);
        if (productId == 0) {
            productDetail = new ProductDetail();
            productDetail.setProductName("");
            productDetail.setSalesPrice(0.);
        } else {
            productDetail = productService.get(productId);
        }
    }
}
