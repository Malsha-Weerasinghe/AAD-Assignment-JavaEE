package javaeepos.bo.custom;

import javaeepos.bo.SuperBO;
import javaeepos.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException;

    public boolean saveItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean updateItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteItem(Connection connection, String itemCode) throws SQLException, ClassNotFoundException;
}
