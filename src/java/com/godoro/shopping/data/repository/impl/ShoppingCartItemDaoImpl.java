

package com.godoro.shopping.data.repository.impl;

import com.godoro.base.BaseRepository;
import com.godoro.shopping.data.entity.ShoppingCartItem;
import com.godoro.shopping.data.repository.ShoppingCartItemDao;



public class ShoppingCartItemDaoImpl extends BaseRepository<ShoppingCartItem, Long> implements ShoppingCartItemDao{

    public ShoppingCartItemDaoImpl() {
        super(ShoppingCartItem.class);
    }

}
