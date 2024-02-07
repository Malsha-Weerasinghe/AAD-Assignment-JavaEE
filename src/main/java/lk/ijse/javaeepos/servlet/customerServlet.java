package lk.ijse.javaeepos.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.javaeepos.dto.CustomerDto;
import lk.ijse.javaeepos.util.SQLUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "customerServlet", value = "/customer/*")
public class customerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
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

            String sql = "INSERT INTO customer (cusID, cusName, cusAddress, cusSalary) VALUES (?, ?, ?, ?)";
            Boolean result = SQLUtil.execute(sql, customerDto.getId(), customerDto.getName(), customerDto.getAddress(), customerDto.getSalary());

            if (result) {
                response.getWriter().println("Customer has been saved successfully");
            } else {
                response.getWriter().println("Failed to save customer");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
