package javaeepos.bo.custom;

import javaeepos.bo.SuperBO;
import javaeepos.dto.CustomerDto;
import javaeepos.dto.ItemDTO;
import javaeepos.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBO extends SuperBO {

    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    public boolean purchaseOrder(Connection connection, OrderDTO dto)throws SQLException, ClassNotFoundException;

    public ArrayList<OrderDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException;
}
