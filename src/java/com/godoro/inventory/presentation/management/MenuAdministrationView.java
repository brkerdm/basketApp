

package com.godoro.inventory.presentation.management;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
public class MenuAdministrationView {
    
    private MenuModel model;

    public MenuModel getModel() {
        return model;
    }
    
    @PostConstruct
    public void init() {
         model = new DefaultMenuModel();
         
         //First submenu
        DefaultSubMenu subMenu = new DefaultSubMenu("Menu");
         
        DefaultMenuItem itemCategory = new DefaultMenuItem("Product Category");
        itemCategory.setUrl("/faces/management/CategorySummary.xhtml");
        itemCategory.setIcon("ui-icon-home");
        subMenu.addElement(itemCategory);
        
        DefaultMenuItem itemProduct = new DefaultMenuItem("Product List");
        itemProduct.setUrl("/faces/management/ProductSummary.xhtml");
        itemProduct.setIcon("ui-icon-home");
        subMenu.addElement(itemProduct);
         
        model.addElement(subMenu);
    }

}
