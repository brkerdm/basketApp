

package com.godoro.inventory.presentation.portal;

import com.godoro.inventory.business.service.CategoryService;
import com.godoro.inventory.business.view.CategoryDetail;
import com.godoro.inventory.business.view.CategorySummary;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named
@ViewScoped
public class MenuPortal implements Serializable {
    
     private TreeNode rootNode;
     private CategorySummary categorySummary;

    public CategorySummary getCategorySummary() {
        return categorySummary;
    }

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }
     
    @Inject
    transient private CategoryService categoryService;
    
    @PostConstruct
    public void init() {
        categorySummary = categoryService.getParentCategories();
        rootNode = new DefaultTreeNode("Kategoriler", null);
        if (categorySummary.getCategoryList() != null && !categorySummary.getCategoryList().isEmpty()) {
            for (CategoryDetail cat : categorySummary.getCategoryList()) {
                createNode(cat, rootNode);    
            }  
        }
 
    }
    
     private void createNode(CategoryDetail categoryDetail, TreeNode parent) {
        TreeNode node = new DefaultTreeNode(categoryDetail, parent); 
       // Create Dummy node, just to make the parent node expandable
        new DefaultTreeNode("DUMMY", node);
    }
     
    public void onNodeExpand(NodeExpandEvent event) {  
         CategoryDetail parentCat = (CategoryDetail) event.getTreeNode().getData();
         DefaultTreeNode parent = (DefaultTreeNode) event.getTreeNode();
         CategorySummary categoryView = categoryService.getChildCategories(parentCat.getCategoryId());
         if (categoryView.getCategoryList() == null) {
             return;
         }
         
         if (parent.getChildCount() == 1 && parent.getChildren().get(0).getData().toString().equals("DUMMY")) {
             parent.getChildren().remove(0);
             for (CategoryDetail category : categoryView.getCategoryList()) {
                createNode(category, parent);
             }
         }
    }
     

}
