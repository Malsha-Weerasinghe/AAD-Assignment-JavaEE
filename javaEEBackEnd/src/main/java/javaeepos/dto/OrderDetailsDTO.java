package javaeepos.dto;

import java.io.Serializable;

public class OrderDetailsDTO implements Serializable {
    private String itemCode;
    private String orderID;
    private Integer qty;
    private Double unitPrice;

    public OrderDetailsDTO(String itemCode, String orderID, Integer qty, Double unitPrice) {
        this.itemCode = itemCode;
        this.orderID = orderID;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public OrderDetailsDTO() {
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "itemCode='" + itemCode + '\'' +
                ", orderID='" + orderID + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
