/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.inventory.business.service;

import com.godoro.inventory.business.view.ProductDetail;
import com.godoro.inventory.business.view.ProductSummary;


/**
 *
 * @author burak
 */
public interface ProductService {
    
     public ProductSummary getProductByCategoryId(long categoryId);
     
     public int getCountByCategoryId(long categoryId);
     
      public void create(ProductDetail productView);
     
     public void update(ProductDetail productView);
     
     public void remove(long productId);
     
     public ProductDetail get(long productId);
     
     public ProductDetail getByReference(long productId);
     
     public ProductSummary getList();
     
     public ProductSummary getList(int start, int offset);
     
     public int getCount();
}
