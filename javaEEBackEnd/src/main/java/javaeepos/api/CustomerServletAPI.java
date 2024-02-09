package javaeepos.api;


import jakarta.json.*;
import javaeepos.bo.BOFactory;
import javaeepos.bo.custom.CustomerBO;
import javaeepos.dto.CustomerDto;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/customers")
public class CustomerServletAPI extends HttpServlet {

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("application/json");


        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");


        try (Connection connection = pool.getConnection()){
            PrintWriter writer = resp.getWriter();

            JsonArrayBuilder allCustomers = Json.createArrayBuilder();

            ArrayList<CustomerDto> all = customerBO.getAllCustomers(connection);

            for (CustomerDto customerDTO:all){
                JsonObjectBuilder customer = Json.createObjectBuilder();

                customer.add("cusId",customerDTO.getCusId());
                customer.add("cusName",customerDTO.getCusName());
                customer.add("cusAddress",customerDTO.getCusAddress());
                customer.add("cusSalary",customerDTO.getCusSalary());

                allCustomers.add(customer.build());
            }
//            Json json = JsonBuilder.create();
//            jsonb.toJson(customerList,resp.getWriter());

            writer.print(allCustomers.build());


        } catch (ClassNotFoundException e) {
            resp.getWriter().println(e.getMessage());
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.addHeader("Access-Control-Allow-Origin","*");


        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");


        String cusId = req.getParameter("cusId");
        String cusName = req.getParameter("cusName");
        String cusAddress = req.getParameter("cusAddress");
        String cusSalary = req.getParameter("cusSalary");

        System.out.printf("cusId=%s ,cusName=%s ,cusAddress=%s\n" , cusId,cusName,cusAddress);


        try (Connection connection = pool.getConnection()){
            PreparedStatement stn = connection.prepareStatement("INSERT INTO customer(cusId,cusName,cusAddress,cusSalary) VALUES(?,?,?,?)");

            stn.setString(1,cusId);
            stn.setString(2,cusName);
            stn.setString(3,cusAddress);
            stn.setString(4,cusSalary);

            stn.executeUpdate();
            resp.getWriter().write("print!!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        String id = req.getParameter("id");

        try(Connection connection = pool.getConnection()) {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM customer WHERE cusId=?");

            stm.setString(1,id);

            if(stm.executeUpdate() != 0){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else{
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete the customer!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String cusId = jsonObject.getString("cusId");
        String cusName = jsonObject.getString("cusName");
        String cusAddress = jsonObject.getString("cusAddress");
        Double cusSalary = Double.valueOf(jsonObject.getString("cusSalary"));

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");


        System.out.printf("id=%s ,name=%s ,address=%s ,salary=%s \n" , cusId,cusName,cusAddress,cusSalary);

        try(Connection connection = pool.getConnection()) {
            PreparedStatement stn = connection.prepareStatement("UPDATE customer SET cusName=?,cusAddress=?,cusSalary=? WHERE cusId=?");


            stn.setString(1,cusName);
            stn.setString(2,cusAddress);
            stn.setDouble(3,cusSalary);
            stn.setString(4,cusId);

            stn.executeUpdate();
            resp.getWriter().write("print!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
