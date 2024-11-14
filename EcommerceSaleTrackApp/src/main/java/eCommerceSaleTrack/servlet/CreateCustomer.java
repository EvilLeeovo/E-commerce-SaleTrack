package eCommerceSaleTrack.servlet;

import eCommerceSaleTrack.dal.CustomersDAO;
import eCommerceSaleTrack.model.Customers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createCustomer")
public class CreateCustomer extends HttpServlet {
    
    protected CustomersDAO customersDao;

    @Override
    public void init() throws ServletException {
        customersDao = CustomersDAO.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        // Just render the JSP.
        req.getRequestDispatcher("/CreateCustomer.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        // Retrieve and validate the customer details.
        String customerId = req.getParameter("customerId");
        String uniqueId = req.getParameter("uniqueId");
        String zipCodePrefix = req.getParameter("zipCodePrefix");
        String city = req.getParameter("city");
        String state = req.getParameter("state");

        if (customerId == null || customerId.trim().isEmpty() || uniqueId == null || uniqueId.trim().isEmpty()) {
            messages.put("success", "Invalid Customer ID or Unique ID");
        } else {
            // Create the Customer.
            try {
                Customers customer = new Customers(customerId, uniqueId, zipCodePrefix, city, state);
                customer = customersDao.create(customer);
                messages.put("success", "Successfully created customer with ID " + customerId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/CreateCustomer.jsp").forward(req, resp);
    }
}
