/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.inventory.data.repository;

import com.godoro.base.GenericRepository;
import com.godoro.inventory.data.entity.Product;
import java.util.List;

/**
 *
 * @author burak
 */
public interface ProductDao extends GenericRepository<Product, Long>{
    
     public List<Product> getProductByCategoryId(long categoryId);
     
     public int getCountByCategoryId(long categoryId);
    
}
