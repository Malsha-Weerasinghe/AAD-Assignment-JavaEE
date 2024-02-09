package javaeepos.bo.custom;

import javaeepos.bo.SuperBO;
import javaeepos.dto.CustomerDto;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public ArrayList<CustomerDto> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException;

    public boolean saveCustomer(Connection connection, CustomerDto customerDTO) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(Connection connection, CustomerDto customerDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(Connection connection, String customerID) throws SQLException, ClassNotFoundException;
}