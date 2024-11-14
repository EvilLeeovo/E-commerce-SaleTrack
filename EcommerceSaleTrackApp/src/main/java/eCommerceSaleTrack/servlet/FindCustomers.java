package eCommerceSaleTrack.servlet;

import eCommerceSaleTrack.dal.*;
import eCommerceSaleTrack.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findcustomers")
public class FindCustomers extends HttpServlet {
    
    protected CustomersDAO customersDAO;

    @Override
    public void init() throws ServletException {
        customersDAO = CustomersDAO.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        List<Customers> customers = new ArrayList<>();
        
        // Retrieve and validate city.
        // City is retrieved from the URL query string.
        String city = req.getParameter("city");
        if (city == null || city.trim().isEmpty()) {
            messages.put("success", "Please enter a valid city.");
        } else {
            // Retrieve Customers by city and store as a message.
            try {
                customers = customersDAO.getCustomersByCity(city);
                if (customers.isEmpty()) {
                    messages.put("success", "No customers found for city: " + city);
                } else {
                    messages.put("success", "Displaying results for city: " + city);
                }
                messages.put("previousCity", city);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.setAttribute("customers", customers);
        
        req.getRequestDispatcher("/FindCustomers.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        List<Customers> customers = new ArrayList<>();
        
        // Retrieve and validate city.
        // City is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindCustomers.jsp).
        String city = req.getParameter("city");
        if (city == null || city.trim().isEmpty()) {
            messages.put("success", "Please enter a valid city.");
        } else {
            // Retrieve Customers by city and store as a message.
            try {
                customers = customersDAO.getCustomersByCity(city);
                if (customers.isEmpty()) {
                    messages.put("success", "No customers found for city: " + city);
                } else {
                    messages.put("success", "Displaying results for city: " + city);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.setAttribute("customers", customers);
        
        req.getRequestDispatcher("/FindCustomers.jsp").forward(req, resp);
    }
}
