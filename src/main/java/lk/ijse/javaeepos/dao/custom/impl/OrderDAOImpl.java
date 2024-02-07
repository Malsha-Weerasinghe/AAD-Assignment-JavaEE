package lk.ijse.javaeepos.dao.custom.impl;

import lk.ijse.javaeepos.dao.DAOFactory;
import lk.ijse.javaeepos.dao.DBConnectionPool;
import lk.ijse.javaeepos.dao.SQLUtil;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Orders> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Orders> allOrders = new ArrayList<>();
        ResultSet rst = SQLUtil.execute(connection,"SELECT * FROM Orders");
        while (rst.next()) {
            Orders orders = new Orders(rst.getString(1), rst.getDate(2), rst.getString(3));
            allOrders.add(orders);
        }
        return allOrders;
    }

    @Override
    public boolean save(Connection connection, Orders entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"INSERT INTO Orders VALUES (?,?,?)",entity.getOrderID(), entity.getOrderDate(),entity.getCusID());
    }

    @Override
    public boolean update(Connection connection, Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Connection connection, String Id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
