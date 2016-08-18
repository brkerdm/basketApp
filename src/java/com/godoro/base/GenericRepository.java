/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godoro.base;

import java.util.List;

/**
 *
 * @author burak
 */
public interface GenericRepository<E,K> {
    
    public void persist(E entity);
    
    public void merge(E entity);
    
    public void remove(K key);
    
    public E find(K key);
    
    public E findByReference(K key);
    
    public List<E> getList();
    
    public List<E> getList(int start, int offset);
    
    public int getCount();
    
    public void close();
 }
