package lk.ijse.javaeepos.bo.custom.impl;

import lk.ijse.javaeepos.bo.custom.PurchaseOrderBO;
import lk.ijse.javaeepos.dao.DAOFactory;
import lk.ijse.javaeepos.dao.custom.ItemDAO;
import lk.ijse.javaeepos.dao.custom.OrderDAO;
import lk.ijse.javaeepos.dao.custom.OrderDetailsDAO;
import lk.ijse.javaeepos.dto.CustomerDto;
import lk.ijse.javaeepos.dto.ItemDTO;
import lk.ijse.javaeepos.dto.OrderDTO;
import lk.ijse.javaeepos.dto.OrderDetailsDTO;
import lk.ijse.javaeepos.entity.OrderDetails;
import lk.ijse.javaeepos.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    OrderDAO orderDAO= (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean purchaseOrder(Connection connection, OrderDTO dto) throws SQLException, ClassNotFoundException {
        connection.setAutoCommit(false);
        if (orderDAO.save(connection,new Orders(dto.getOrderID(),dto.getDate(),dto.getCustomerID()))){
            for (OrderDetailsDTO orderDetailsDTO: dto.getOrderDetailsDTOList()) {
                if (orderDetailsDAO.save(connection,new OrderDetails(orderDetailsDTO.getItemCode(),orderDetailsDTO.getOrderID(),orderDetailsDTO.getQty(),orderDetailsDTO.getUnitPrice()))){
                    connection.commit();
                    connection.setAutoCommit(true);
                    return true;
                }else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
        }
        connection.rollback();
        connection.setAutoCommit(true);
        return false;

    }

    @Override
    public ArrayList<OrderDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> allOrders= new ArrayList<>();
        ArrayList<Orders> all = orderDAO.getAll(connection);
        for (Orders c : all) {
            allOrders.add(new OrderDTO(c.getOrderID(),c.getOrderDate(),c.getCusID()));
        }
        return allOrders;
    }
}
