

package com.godoro.shopping.data.repository.impl;

import com.godoro.base.BaseRepository;
import com.godoro.shopping.data.entity.ShoppingCart;
import com.godoro.shopping.data.repository.ShoppingCartDao;


public class ShoppingCartDaoImpl extends BaseRepository<ShoppingCart, Long> implements ShoppingCartDao{

    public ShoppingCartDaoImpl() {
        super(ShoppingCart.class);
    }

}
