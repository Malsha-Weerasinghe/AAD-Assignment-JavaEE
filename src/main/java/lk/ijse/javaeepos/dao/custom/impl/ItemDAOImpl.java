package lk.ijse.javaeepos.dao.custom.impl;



import lk.ijse.javaeepos.dao.SQLUtil;
import lk.ijse.javaeepos.dao.custom.ItemDAO;
import lk.ijse.javaeepos.entity.Items;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Items> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Items> allItems = new ArrayList<>();
        ResultSet rst = SQLUtil.execute(connection,"SELECT * FROM item");
        while (rst.next()) {
            Items item = new Items(rst.getString(1), rst.getString(2), rst.getDouble(3),  rst.getInt(4));
            allItems.add(item);
        }
        return allItems;
    }

    @Override
    public boolean save(Connection connection, Items entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection, "INSERT INTO item VALUES (?,?,?,?)", entity.getItemCode(), entity.getItemName(), entity.getItemPrice(), entity.getItemQuantity());
    }

    @Override
    public boolean update(Connection connection, Items entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection, "UPDATE item SET item_name=?, unit_price=?, qty_on_hnd=? WHERE item_ID=?",entity.getItemName() , entity.getItemPrice(), entity.getItemQuantity(), entity.getItemCode());
    }

    @Override
    public boolean delete(Connection connection, String Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection, "DELETE FROM item WHERE item_ID=?", Id);
    }
}
