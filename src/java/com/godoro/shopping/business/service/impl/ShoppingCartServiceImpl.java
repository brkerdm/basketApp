

package com.godoro.shopping.business.service.impl;

import com.godoro.inventory.data.repository.ProductDao;
import com.godoro.shopping.business.service.ShoppingCartService;
import com.godoro.shopping.business.view.ShoppingCartDetail;
import com.godoro.shopping.business.view.ShoppingCartItemDetail;
import com.godoro.shopping.data.entity.ShoppingCart;
import com.godoro.shopping.data.entity.ShoppingCartItem;
import com.godoro.shopping.data.repository.ShoppingCartDao;
import com.godoro.shopping.data.repository.ShoppingCartItemDao;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ShoppingCartServiceImpl implements ShoppingCartService{
    
    private ShoppingCartDao shoppingCartDao;
    
    @Inject
    private ProductDao productDao;
    
    @Inject
    private ShoppingCartItemDao shoppingCartItemDao;
    
    @Inject
    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    public ShoppingCartServiceImpl() {
    }

    public ShoppingCartDao getShoppingCartDao() {
        return shoppingCartDao;
    }
    
    

    @Override
    public long create(ShoppingCartDetail shoppingCartDetail) {
        ShoppingCart shoppingCart = new ShoppingCart();
        
        shoppingCart.setCardNumber(shoppingCartDetail.getCardNumber());
        shoppingCart.setPersonName(shoppingCartDetail.getPersonName());
        shoppingCart.setTotalAmount(shoppingCartDetail.getTotalAmount());
        
        if (shoppingCartDetail.getItemList() != null && !shoppingCartDetail.getItemList().isEmpty()) {
            shoppingCart.setCartItemList(new ArrayList<ShoppingCartItem>());
            for (ShoppingCartItemDetail itemDetail : shoppingCartDetail.getItemList()) {
                ShoppingCartItem item = new ShoppingCartItem();
                item.setProduct(productDao.find(itemDetail.getProductId()));
                item.setSalesQuantity(itemDetail.getSalesQuantity());
                item.setAmount(itemDetail.getAmount());
                item.setCart(shoppingCart);
                shoppingCart.getCartItemList().add(item);
            }
            
        }
  
        shoppingCartDao.persist(shoppingCart);
        return shoppingCart.getCartId();
        
    }

    @Override
    public void update(ShoppingCartDetail shoppingCartDetail) {
       ShoppingCart shoppingCart = shoppingCartDao.find(shoppingCartDetail.getCartId());
       if (shoppingCart == null) {
           //throw error
           return;
       }
       shoppingCart.setCardNumber(shoppingCartDetail.getCardNumber());
       shoppingCart.setPersonName(shoppingCartDetail.getPersonName());
       shoppingCart.setTotalAmount(shoppingCartDetail.getTotalAmount());
       
       shoppingCart.setCartItemList(new ArrayList<ShoppingCartItem>());
       for (ShoppingCartItemDetail itemDetail : shoppingCartDetail.getItemList()) {
           ShoppingCartItem item = null;
           if (itemDetail.getItemId() == 0) {
               item = new ShoppingCartItem();
           } else {
               item = shoppingCartItemDao.find(itemDetail.getItemId());
           }
           item.setCart(shoppingCart);
           item.setProduct(productDao.find(itemDetail.getProductId()));
           item.setSalesQuantity(itemDetail.getSalesQuantity());
           item.setAmount(itemDetail.getAmount());
        
           shoppingCart.getCartItemList().add(item);
           
       }
  
       shoppingCartDao.merge(shoppingCart);
    }
    
//    private void calculateTotals(ShoppingCart cart) {
//        double total = 0.;
//         for (ShoppingCartItem item : cart.getCartItemList()) {
//           item.setAmount(item.getSalesQuantity() * item.getProduct().getSalesPrice());
//           total += item.getAmount();
//            
//        }
//        cart.setTotalAmount(total);
//    }

    @Override
    public void remove(long cartId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ShoppingCartDetail get(long cartId) {
       ShoppingCart shoppingCart = shoppingCartDao.find(cartId);
       if (shoppingCart == null) {
           //throw error
           return null;
       }
       return mapEntityToView(shoppingCart);
    }

    @Override
    public ShoppingCartDetail getByReference(long cartId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ShoppingCartDetail getList() {
        ShoppingCart shoppingCart = null;
        List<ShoppingCart> cartList = shoppingCartDao.getList();
        if (cartList == null || cartList.isEmpty()) {
            return null;
        }
        shoppingCart = cartList.get(0);
        return mapEntityToView(shoppingCart);
        
    }
    
    private ShoppingCartDetail mapEntityToView(ShoppingCart shoppingCart) {
        ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail();
        shoppingCartDetail.setCartId(shoppingCart.getCartId());
        shoppingCartDetail.setCardNumber(shoppingCart.getCardNumber());
        shoppingCartDetail.setPersonName(shoppingCart.getPersonName());
        shoppingCartDetail.setTotalAmount(shoppingCart.getTotalAmount());
        shoppingCartDetail.setItemList(new ArrayList<ShoppingCartItemDetail>());
        for (ShoppingCartItem item : shoppingCart.getCartItemList()) {
            ShoppingCartItemDetail itemDetail = new ShoppingCartItemDetail();
            itemDetail.setItemId(item.getItemId());
            itemDetail.setProductId(item.getProduct().getProductId());
            itemDetail.setProductName(item.getProduct().getProductName());
            itemDetail.setSalesPrice(item.getProduct().getSalesPrice());
            itemDetail.setSalesQuantity(item.getSalesQuantity());
            itemDetail.setAmount(item.getAmount());
            shoppingCartDetail.getItemList().add(itemDetail);
        }
        return shoppingCartDetail;
    }

}
