package lk.ijse.javaeepos.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Items {
    private String ItemCode;
    private String ItemName;
    private int ItemQuantity;
    private double ItemPrice;

    public Items(String itemCode, String itemName,  double itemPrice, int itemQuantity) {
        ItemCode = itemCode;
        ItemName = itemName;
        ItemQuantity = itemQuantity;
        ItemPrice = itemPrice;
    }

    public Items() {
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
                ", ItemPrice=" + ItemPrice +
                ", ItemQuantity=" + ItemQuantity +
                '}';
    }
}
