package javaeepos.dto;

import java.io.Serializable;

public class ItemDTO implements Serializable {
    private String ItemCode;
    private String ItemName;

    private double ItemPrice;
    private int ItemQuantity;

    public ItemDTO(String itemCode, String itemName,  double itemPrice, int itemQuantity) {
        ItemCode = itemCode;
        ItemName = itemName;
        ItemPrice = itemPrice;
        ItemQuantity = itemQuantity;
    }

    public ItemDTO() {
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getItemQuantity() {
        return ItemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        ItemQuantity = itemQuantity;
    }

    public double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(double itemPrice) {
        ItemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ItemCode='" + ItemCode + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", itemQuantity=" + ItemQuantity +
                ", ItemPrice=" + ItemPrice +
                '}';
    }
}
