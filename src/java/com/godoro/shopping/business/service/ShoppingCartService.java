/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.shopping.business.service;

import com.godoro.shopping.business.view.ShoppingCartDetail;

/**
 *
 * @author burak
 */
public interface ShoppingCartService {
    
    public long create(ShoppingCartDetail shoppingCartDetail);
    
    public void update(ShoppingCartDetail shoppingCartDetail);
    
    public void remove(long cartId);
    
    public ShoppingCartDetail get(long cartId);
    
    public ShoppingCartDetail getByReference(long cartId);
    
    public ShoppingCartDetail getList();
    
    
    
    
}
