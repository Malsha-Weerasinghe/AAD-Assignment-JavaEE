package lk.ijse.javaeepos.bo.custom;

import lk.ijse.javaeepos.bo.SuperBO;
import lk.ijse.javaeepos.dto.CustomerDto;
import lk.ijse.javaeepos.dto.ItemDTO;
import lk.ijse.javaeepos.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBO extends SuperBO {

    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    public boolean purchaseOrder(Connection connection, OrderDTO dto)throws SQLException, ClassNotFoundException;

    public ArrayList<OrderDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException;
}
