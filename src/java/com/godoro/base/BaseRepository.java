

package com.godoro.base;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class BaseRepository<E extends BaseEntity, K extends Serializable> implements GenericRepository<E, K>{
    
    protected EntityManager entityManager;
    private final static String SELECT = "Select %s from %s as %s";
    private final static String COUNT = "Select count(%s) from %s as %s";
    protected Class<E> entityClass;
        
   public BaseRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
        entityManager = PersistenceUtilities.getFactory().createEntityManager();
    }
    
    @Override
    public void persist(E entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
    
    @Override
    public void merge(E entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }
    
    @Override
    public void remove(K key) {
        E entity=entityManager.getReference(entityClass, key);
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
    
    @Override
    public E find(K key) {
       return entityManager.find(entityClass, key);
    }
    
    @Override
    public E findByReference(K key) {
       return entityManager.getReference(entityClass, key);
    }
    
    
   
    public String createSelect() {
        String entityName = entityClass.getSimpleName();
        String variableName = entityName.toLowerCase(Locale.US);
        String jpql = String.format(SELECT, variableName, entityName, variableName);
        return jpql;
       
    }
    
    public String createCount() {
        String entityName = entityClass.getSimpleName();
        String variableName = entityName.toLowerCase(Locale.US);
        String jpql = String.format(COUNT, variableName, entityName, variableName);
        return jpql;
       
    }
    
    @Override
    public List<E> getList() {
        String jpql = createSelect();
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
        
    }
    
    @Override
    public List<E> getList(int start, int offset) {
        String jpql = createSelect();
        Query query = entityManager.createQuery(jpql);
        query.setFirstResult(start);
        query.setMaxResults(offset);
        return query.getResultList();
        
    }
    
    @Override
    public int getCount() {
        String jpql = createCount();
        Query query = entityManager.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
    
    @Override
    public void close() {
        entityManager.close();
    }
}
