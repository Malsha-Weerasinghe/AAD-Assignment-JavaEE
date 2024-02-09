package javaeepos.bo.custom.impl;

import javaeepos.bo.custom.CustomerBO;
import javaeepos.dao.DAOFactory;
import javaeepos.dao.custom.CustomerDAO;
import javaeepos.dto.CustomerDto;
import javaeepos.entity.Customer;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public ArrayList<CustomerDto> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto> allCustomers= new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll(connection);
        for (Customer c : all) {
            allCustomers.add(new CustomerDto(c.getCusId(),c.getCusName(),c.getCusAddress(),c.getCusSalary()));
        }
        return allCustomers;
    }

    @Override
    public boolean saveCustomer(Connection connection, CustomerDto customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(connection,new Customer(customerDTO.getCusId(),customerDTO.getCusName(),customerDTO.getCusAddress(),customerDTO.getCusSalary()));
    }

    @Override
    public boolean updateCustomer(Connection connection, CustomerDto customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(connection,new Customer(customerDTO.getCusId(),customerDTO.getCusName(),customerDTO.getCusAddress(),customerDTO.getCusSalary()));
    }

    @Override
    public boolean deleteCustomer(Connection connection, String customerID) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(connection,customerID);
    }
}
