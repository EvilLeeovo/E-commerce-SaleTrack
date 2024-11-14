package eCommerceSaleTrack.servlet;

import eCommerceSaleTrack.dal.CustomersDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customerDelete")
public class CustomerDelete extends HttpServlet {
    
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Customer");        
        req.getRequestDispatcher("/CustomerDelete.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        // Retrieve and validate customer ID.
        String customerId = req.getParameter("customerId");
        if (customerId == null || customerId.trim().isEmpty()) {
            messages.put("title", "Invalid Customer ID");
            messages.put("disableSubmit", "true");
        } else {
            try {
                // Attempt to delete the customer by ID
                boolean deleted = customersDao.deleteByCustomerId(customerId);
                // Update the message.
                if (deleted) {
                    messages.put("title", "Successfully deleted Customer ID: " + customerId);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("title", "Failed to delete Customer ID: " + customerId);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/CustomerDelete.jsp").forward(req, resp);
    }
}
