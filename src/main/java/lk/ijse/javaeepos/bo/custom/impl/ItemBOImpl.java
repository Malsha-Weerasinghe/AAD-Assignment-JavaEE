package lk.ijse.javaeepos.bo.custom.impl;

import lk.ijse.javaeepos.bo.custom.ItemBO;
import lk.ijse.javaeepos.dao.DAOFactory;
import lk.ijse.javaeepos.dao.custom.ItemDAO;
import lk.ijse.javaeepos.dto.ItemDTO;
import lk.ijse.javaeepos.entity.Items;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems= new ArrayList<>();
        ArrayList<Items> all = itemDAO.getAll(connection);
        for (Items c : all) {
            allItems.add(new ItemDTO(c.getItemCode(),c.getItemName(),c.getItemPrice(),c.getItemQuantity()));
        }
        return allItems;
    }

    @Override
    public boolean saveItem(Connection connection,ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.save(connection,new Items(itemDTO.getItemCode(),itemDTO.getItemName(),itemDTO.getItemPrice(),itemDTO.getItemQuantity()));
    }

    @Override
    public boolean updateItem(Connection connection,ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(connection,new Items(itemDTO.getItemCode(),itemDTO.getItemName(),itemDTO.getItemPrice(),itemDTO.getItemQuantity()));
    }

    @Override
    public boolean deleteItem(Connection connection,String itemCode) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(connection,itemCode);
    }
}
