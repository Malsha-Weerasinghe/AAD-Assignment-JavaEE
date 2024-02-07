package lk.ijse.javaeepos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private String orderID;
    private String orderDate;
    private String cusID;

}
