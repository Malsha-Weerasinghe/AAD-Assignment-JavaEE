package javaeepos.dao.custom.impl;


import javaeepos.dao.SQLUtil;
import javaeepos.dao.custom.OrderDetailsDAO;
import javaeepos.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ArrayList<OrderDetails> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Connection connection, OrderDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"INSERT INTO order_detail VALUES (?,?,?,?)",entity.getItemCode(), entity.getOrderID(),entity.getQty(),entity.getUnitPrice());

    }

    @Override
    public boolean update(Connection connection, OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Connection connection, String Id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
