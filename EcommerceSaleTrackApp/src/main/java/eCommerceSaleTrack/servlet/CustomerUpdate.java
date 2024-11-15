package eCommerceSaleTrack.servlet;

import eCommerceSaleTrack.dal.*;
import eCommerceSaleTrack.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customerupdate")
public class CustomerUpdate extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    protected CustomersDAO customersDao;
    
    @Override
    public void init() throws ServletException {
        // Initialize CustomersDAO instance
        customersDao = CustomersDAO.getInstance();
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Prepare a map to store messages to be displayed in the JSP
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        // Retrieve customerId from the request and validate
        String customerId = req.getParameter("customerId");
        if (customerId == null || customerId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Customer ID.");
        } else {
            try {
                // Fetch the customer using the provided customerId
                Customers customer = customersDao.getCustomerById(customerId);
                if (customer == null) {
                    messages.put("success", "Customer ID does not exist.");
                } else {
                    // Set the customer object as a request attribute for display
                    req.setAttribute("customer", customer);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        
        // Forward the request to the JSP page for rendering
        req.getRequestDispatcher("/CustomerUpdate.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Prepare a map to store messages to be displayed in the JSP
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        // Retrieve customerId from the request and validate
        String customerId = req.getParameter("customerId");
        if (customerId == null || customerId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Customer ID.");
        } else {
            try {
                // Fetch the customer using the provided customerId
                Customers customer = customersDao.getCustomerById(customerId);
                if (customer == null) {
                    messages.put("success", "Customer ID does not exist. No update to perform.");
                } else {
                    // Retrieve new city and state from the request parameters
                    String newCity = req.getParameter("city");
                    String newState = req.getParameter("state");
                    if (newCity == null || newCity.trim().isEmpty() || newState == null || newState.trim().isEmpty()) {
                        messages.put("success", "Please enter a valid City and State.");
                    } else {
                        // Update the customer's city and state in the database
                        customer = customersDao.updateCustomerCityAndState(customer, newCity, newState);
                        messages.put("success", "Successfully updated Customer ID: " + customerId);
                    }
                }
                // Set the updated customer object as a request attribute
                req.setAttribute("customer", customer);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        
        // Forward the request to the JSP page for rendering
        req.getRequestDispatcher("/CustomerUpdate.jsp").forward(req, resp);
    }
}
