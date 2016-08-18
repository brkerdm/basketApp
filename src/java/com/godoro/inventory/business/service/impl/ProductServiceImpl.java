

package com.godoro.inventory.business.service.impl;

import com.godoro.inventory.business.service.ProductService;
import com.godoro.inventory.business.view.ProductDetail;
import com.godoro.inventory.business.view.ProductSummary;
import com.godoro.inventory.data.entity.Product;
import com.godoro.inventory.data.repository.CategoryDao;
import com.godoro.inventory.data.repository.ProductDao;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ProductServiceImpl implements ProductService {
    
    private ProductDao productDao;
    
    @Inject
    private CategoryDao categoryDao;
    
    @Inject
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductServiceImpl() {
    }
    
    

    public ProductDao getProductDao() {
        return productDao;
    }
   
    private ProductSummary mapListToView(List<Product> productList) {
        ProductSummary productSummary = new ProductSummary();
        if (productList != null && !productList.isEmpty()) {
            productSummary.setProductList(new ArrayList<ProductDetail>());
            for (Product product : productList) {
                ProductDetail productDetail = new ProductDetail();
                productDetail.setProductId(product.getProductId());
                productDetail.setProductName(product.getProductName());
                productDetail.setSalesPrice(product.getSalesPrice());
                if (product.getCategory() != null) {
                    productDetail.setCategoryId(product.getCategory().getCategoryId());
                    productDetail.setCategoryName(product.getCategory().getCategoryName());
                }
                productSummary.getProductList().add(productDetail);
            }
        }
        return productSummary;
    
    }
    
    @Override
    public ProductSummary getProductByCategoryId(long categoryId) {
        List<Product> productList = getProductDao().getProductByCategoryId(categoryId);
        return mapListToView(productList);
    } 

    @Override
    public int getCountByCategoryId(long categoryId) {
        return getProductDao().getCountByCategoryId(categoryId);
    }

    @Override
    public void create(ProductDetail productView) {
        Product product = new Product();
        product.setProductName(productView.getProductName());
        product.setSalesPrice(productView.getSalesPrice());
        if (productView.getCategoryId() != 0) {
            product.setCategory(categoryDao.find(productView.getCategoryId()));
        }
        getProductDao().persist(product);
    }

    @Override
    public void update(ProductDetail productView) {
        Product product = null;
        if (productView.getProductId() == 0) {
            //throw error
            return;
        }
        product = getProductDao().find(productView.getProductId());
        product.setProductName(productView.getProductName());
        product.setSalesPrice(productView.getSalesPrice());
        if (productView.getCategoryId() > 0) {
            product.setCategory(categoryDao.find(productView.getCategoryId()));
        } else {
            product.setCategory(null);
        }
        getProductDao().merge(product);
    }

    @Override
    public void remove(long productId) {
        getProductDao().remove(productId);
    }

    @Override
    public ProductDetail get(long productId) {
       Product product = getProductDao().find(productId);
       if (product == null) {
           //throw error
           return null;
       }
       ProductDetail productDetail = new ProductDetail();
       productDetail.setProductId(product.getProductId());
       productDetail.setProductName(product.getProductName());
       productDetail.setSalesPrice(product.getSalesPrice());
       if (product.getCategory() != null) {
           productDetail.setCategoryId(product.getCategory().getCategoryId());
           productDetail.setCategoryName(product.getCategory().getCategoryName());
       }
       return productDetail;
    }

    @Override
    public ProductDetail getByReference(long productId) {
        Product product = getProductDao().findByReference(productId);
       if (product == null) {
           //throw error
           return null;
       }
       ProductDetail productDetail = new ProductDetail();
       productDetail.setProductId(product.getProductId());
       productDetail.setProductName(product.getProductName());
       productDetail.setSalesPrice(product.getSalesPrice());
       if (product.getCategory() != null) {
           productDetail.setCategoryId(product.getCategory().getCategoryId());
           productDetail.setCategoryName(product.getCategory().getCategoryName());
       }
       return productDetail;
    }

    @Override
    public ProductSummary getList() {
       List<Product> productList = getProductDao().getList();
       return mapListToView(productList);
    }

    @Override
    public ProductSummary getList(int start, int offset) {
       List<Product> productList = getProductDao().getList(start, offset);
       return mapListToView(productList);
    }

    @Override
    public int getCount() {
        return getProductDao().getCount();
    }

}
