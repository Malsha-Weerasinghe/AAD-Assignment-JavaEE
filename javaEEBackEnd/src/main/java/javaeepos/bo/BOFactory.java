package javaeepos.bo;

import javaeepos.bo.custom.impl.CustomerBOImpl;
import javaeepos.bo.custom.impl.ItemBOImpl;
import javaeepos.bo.custom.impl.PurchaseOrderBOImpl;



public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){

        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,PO
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PO:
                return new PurchaseOrderBOImpl();
            default:
                return null;        }
        }
}
