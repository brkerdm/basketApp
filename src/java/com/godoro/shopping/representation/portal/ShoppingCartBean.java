

package com.godoro.shopping.representation.portal;

import com.godoro.inventory.business.service.ProductService;
import com.godoro.inventory.business.view.ProductDetail;
import com.godoro.library.FacesUtilities;
import com.godoro.shopping.business.service.ShoppingCartService;
import com.godoro.shopping.business.view.ShoppingCartDetail;
import com.godoro.shopping.business.view.ShoppingCartItemDetail;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ShoppingCartBean implements Serializable {
    
    private ShoppingCartDetail shoppingCart;
    
    @Inject
    transient private ShoppingCartService shoppingCartService;
    
    @Inject
    transient private ProductService productService;

    public ShoppingCartDetail getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCartDetail(ShoppingCartDetail shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
    @PostConstruct
    public void init() {
        shoppingCart = shoppingCartService.getList();
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCartDetail();
            shoppingCart.setCardNumber("");
            shoppingCart.setPersonName("");
            shoppingCart.setTotalAmount(0.);
            shoppingCart.setItemList(new ArrayList<ShoppingCartItemDetail>());
        }
    }
    
     public String add() {
         long productId = FacesUtilities.getParameter("productId", 0);
         if (productId == 0) {
             //error message
             return null;
         }
         ShoppingCartItemDetail cartItemDetail = new ShoppingCartItemDetail();
         ProductDetail product = productService.get(productId);
         cartItemDetail.setProductId(product.getProductId());
         cartItemDetail.setProductName(product.getProductName());
         cartItemDetail.setSalesPrice(product.getSalesPrice());
         if (shoppingCart.getItemList().isEmpty()) {
             cartItemDetail.setSalesQuantity(1);
             shoppingCart.getItemList().add(cartItemDetail);
         } else {
             List<ShoppingCartItemDetail> itemList = shoppingCart.getItemList();
             boolean isExist = false;
             for (ShoppingCartItemDetail itemDetail : itemList) {
                 if (itemDetail.getProductId() == cartItemDetail.getProductId()) {
                     itemDetail.setSalesQuantity(itemDetail.getSalesQuantity() + 1);
                     cartItemDetail = itemDetail;
                     isExist = true;
                     break;
                 }
             }
             if (!isExist) {
                 cartItemDetail.setSalesQuantity(1);
                 shoppingCart.getItemList().add(cartItemDetail);
             }
         }
         calculateTotals();
         if (shoppingCart.getCartId() == 0) {
             long shoppingCartId = shoppingCartService.create(shoppingCart);
             shoppingCart.setCartId(shoppingCartId);
         } else {
             shoppingCartService.update(shoppingCart);
         }
         return "ShoppingCart.xhtml?faces-redirect=true";
     }
     
     public void update() {
         calculateTotals();
         shoppingCartService.update(shoppingCart);
     }
     
     public void remove(ShoppingCartItemDetail cartItem) {
         for(ShoppingCartItemDetail item : shoppingCart.getItemList()) {
             if (item.getProductId() == cartItem.getProductId()) {
                 shoppingCart.getItemList().remove(item);
                 break;
             }
         }
         calculateTotals();
         shoppingCartService.update(shoppingCart);
     }
     
     private void calculateTotals() {
         double total = 0.;
         
         for (ShoppingCartItemDetail item : shoppingCart.getItemList()) {
             item.setAmount(item.getSalesPrice() * item.getSalesQuantity());
             total += item.getAmount();
         }
         
         shoppingCart.setTotalAmount(total);
     }
}
