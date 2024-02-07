package lk.ijse.javaeepos.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import lk.ijse.javaeepos.bo.custom.CustomerBO;
import lk.ijse.javaeepos.bo.custom.impl.CustomerBOImpl;
import lk.ijse.javaeepos.dto.CustomerDto;
import lk.ijse.javaeepos.util.SQLUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "customerServlet", value = "/customer/*")
public class customerServlet extends HttpServlet {

    CustomerBO customerBO = new CustomerBOImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/plain");
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonInput = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                jsonInput.append(line);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            CustomerDto customerDto = objectMapper.readValue(jsonInput.toString(), CustomerDto.class);

            Boolean result = customerBO.addCustomer(customerDto);

            if (result) {
                response.getWriter().println("Customer has been saved successfully");
            } else {
                response.getWriter().println("Failed to save customer");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }
    private void getAll(String customerId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setContentType("application/json");

        try {
            String sql = "SELECT * FROM customer WHERE cusID=?";
            ResultSet rst = SQLUtil.execute(sql, customerId);

            PrintWriter writer = response.getWriter();

            JsonArrayBuilder allCustomer = Json.createArrayBuilder();

            while (rst.next()) {
                String id = rst.getString("cusID");
                String name = rst.getString("cusName");
                String address = rst.getString("cusAddress");
                double salary = rst.getDouble("cusSalary");

                JsonObjectBuilder customer = Json.createObjectBuilder();

                customer.add("id", id);
                customer.add("name", name);
                customer.add("address", address);
                customer.add("salary", salary);

                allCustomer.add(customer.build());
            }
            writer.print(allCustomer.build());
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");

        String customerId = request.getParameter("customerId");
        if (customerId != null) {
            getAll(customerId, response);
            return;
        }
        try {
            List<CustomerDto> allCustomers = customerBO.getAllCustomers();

            JsonArrayBuilder allCustomersArray = Json.createArrayBuilder();

            for (CustomerDto customer : allCustomers) {
                JsonObjectBuilder customerObject = Json.createObjectBuilder()
                        .add("id", customer.getId())
                        .add("name", customer.getName())
                        .add("address", customer.getAddress())
                        .add("salary", customer.getSalary());
                allCustomersArray.add(customerObject);
            }

            PrintWriter writer = response.getWriter();
            writer.print(allCustomersArray.build());
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Error in doGet method", e);
        }
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/plain");
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonInput = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                jsonInput.append(line);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            CustomerDto customerDto = objectMapper.readValue(jsonInput.toString(), CustomerDto.class);

            Boolean result = customerBO.updateCustomer(customerDto);
            if (result) {
                response.getWriter().println("Customer has been updated successfully");
            } else {
                response.getWriter().println("Failed to update customer");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/plain");
        try {
            String customerId = request.getPathInfo().substring(1);
            Boolean result = customerBO.deleteCustomer(customerId);

            if (result) {
                response.getWriter().println("Customer has been deleted successfully");
            } else {
                response.getWriter().println("Customer not found or could not be deleted");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error in doDelete method", e);
        }
    }
}
