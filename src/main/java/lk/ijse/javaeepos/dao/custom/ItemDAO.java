package lk.ijse.javaeepos.dao.custom;

import lk.ijse.javaeepos.dao.CrudDAO;
import lk.ijse.javaeepos.entity.Items;

public interface ItemDAO extends CrudDAO<Items> {
    public Boolean updateQty(String id,int qty) throws Exception;
}
