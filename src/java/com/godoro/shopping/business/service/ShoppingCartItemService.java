/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.shopping.business.service;

import com.godoro.shopping.business.view.ShoppingCartItemDetail;

/**
 *
 * @author burak
 */
public interface ShoppingCartItemService {
    
    public void create(ShoppingCartItemDetail shoppingCartItemDetail);
    
    public void update(ShoppingCartItemDetail shoppingCartItemDetail);
    
    public void remove(long cartItemId);
    
    public ShoppingCartItemDetail get(long cartItemId);
    
    public ShoppingCartItemDetail getByReference(long cartId);
    
    public ShoppingCartItemDetail getList();
    
}
