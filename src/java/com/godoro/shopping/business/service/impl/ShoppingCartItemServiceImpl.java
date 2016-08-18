

package com.godoro.shopping.business.service.impl;

import com.godoro.shopping.business.service.ShoppingCartItemService;
import com.godoro.shopping.business.view.ShoppingCartItemDetail;
import com.godoro.shopping.data.repository.ShoppingCartItemDao;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService{
    
    private ShoppingCartItemDao shoppingCartItemDao;

    public ShoppingCartItemServiceImpl() {
    }
    
    @Inject
    public ShoppingCartItemServiceImpl(ShoppingCartItemDao shoppingCartItemDao) {
        this.shoppingCartItemDao = shoppingCartItemDao;
    }
    
    

    @Override
    public void create(ShoppingCartItemDetail shoppingCartItemDetail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ShoppingCartItemDetail shoppingCartItemDetail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(long cartItemId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ShoppingCartItemDetail get(long cartItemId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ShoppingCartItemDetail getByReference(long cartId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ShoppingCartItemDetail getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
