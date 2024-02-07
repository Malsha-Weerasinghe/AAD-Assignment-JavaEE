package lk.ijse.javaeepos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderDetails {
    private String itemCode;
    private String orderID;
    private int quantity;
    private double itemPrice;
}
