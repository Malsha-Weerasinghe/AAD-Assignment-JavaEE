package lk.ijse.javaeepos.api;

import jakarta.json.*;
import lk.ijse.javaeepos.bo.BOFactory;
import lk.ijse.javaeepos.bo.custom.ItemBO;
import lk.ijse.javaeepos.dto.ItemDTO;
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

@WebServlet(urlPatterns = "/item")
public class ItemServletAPI extends HttpServlet {

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("application/json");

        ServletContext servletContext =getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");


        try(Connection connection = pool.getConnection()) {

            PrintWriter writer = resp.getWriter();

            JsonArrayBuilder allItems = Json.createArrayBuilder();

            ArrayList<ItemDTO> all = itemBO.getAllItems(connection);

            for (ItemDTO itemDTO:all) {

                JsonObjectBuilder item = Json.createObjectBuilder();

                item.add("code",itemDTO.getItemCode());
                item.add("desc",itemDTO.getItemName());
                item.add("unitPrice",itemDTO.getItemPrice());
                item.add("qty",itemDTO.getItemQuantity());

                allItems.add(item.build());
            }

            writer.print(allItems.build());


        } catch (ClassNotFoundException e) {
            resp.getWriter().println(e.getMessage());
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin","*");


        ServletContext servletContext =getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");


        String code = req.getParameter("code");
        String name = req.getParameter("name");
        Double price = Double.valueOf(req.getParameter("price"));
        System.out.println("come ::>"+ price+req.getParameter("price"));
        int qty = Integer.parseInt(req.getParameter("qty"));

        System.out.printf("code=%s ,name=%s ,price=%s ,qty=%s\n" , code,name,price,qty);


        try (Connection connection = pool.getConnection()){
            PreparedStatement stn = connection.prepareStatement("INSERT INTO items(itemCode,itemName,itemPrice,itemQuantity) VALUES(?,?,?,?)");

            stn.setString(1,code);
            stn.setString(2,name);
            stn.setDouble(3,price);
            stn.setInt(4,qty);

            stn.executeUpdate();
            resp.getWriter().write("print!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("print put");

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String code = jsonObject.getString("code");
        String description = jsonObject.getString("desc");
        double unitPrice = Double.parseDouble(jsonObject.getString("unitPrice"));
        int qtyOnHand = Integer.parseInt(jsonObject.getString("qty"));
//
        System.out.printf("code=%s ,name=%s ,price=%s ,qty=%s\n" , code,description,unitPrice,qtyOnHand);

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try (Connection connection = pool.getConnection()) {
            PreparedStatement stn = connection.prepareStatement("UPDATE items SET itemName=?,itemPrice=?,itemQuantity=? WHERE itemCode=?");


            stn.setString(1,description);
            stn.setDouble(2,unitPrice);
            stn.setInt(3,qtyOnHand);
            stn.setString(4,code);

            stn.executeUpdate();
            resp.getWriter().write("print!!");
        } catch (SQLException e) {
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

        String code = req.getParameter("code");



        try(Connection connection = pool.getConnection()) {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM items WHERE itemCode=?");

            stm.setString(1,code);

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
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
