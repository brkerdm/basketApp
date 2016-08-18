

package com.godoro.shopping.business.view;

import java.util.List;


public class ShoppingCartDetail {
    
    private long cartId;
    private String cardNumber;
    private String personName;
    private List<ShoppingCartItemDetail> itemList;
    private double totalAmount;

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public List<ShoppingCartItemDetail> getItemList() {
        return itemList;
    }

    public void setItemList(List<ShoppingCartItemDetail> itemList) {
        this.itemList = itemList;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    
    
    
    

}
