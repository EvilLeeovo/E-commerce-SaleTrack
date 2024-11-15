package eCommerceSaleTrack.servlet;

import eCommerceSaleTrack.dal.OrdersDAO;
import eCommerceSaleTrack.model.Orders;
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

@WebServlet("/getOrderHistoryByCustomerId")
public class GetOrderHistoryByCustomerId extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected OrdersDAO ordersDao;
	
	@Override
	public void init() throws ServletException {
		ordersDao = OrdersDAO.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        List<Orders> orders = new ArrayList<>();
        
        String customerId = req.getParameter("customerId");
        if (customerId == null || customerId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid customer ID.");
        } else {
        	try {
            	orders = ordersDao.getOrdersByCustomerId(customerId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying order history for Customer ID: " + customerId);
        	messages.put("previousCustomerId", customerId);
        }
        req.setAttribute("orders", orders);
        
        req.getRequestDispatcher("/OrderHistory.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        List<Orders> orders = new ArrayList<>();
        
        String customerId = req.getParameter("customerId");
        if (customerId == null || customerId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid customer ID.");
        } else {
        	try {
            	orders = ordersDao.getOrdersByCustomerId(customerId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying order history for Customer ID: " + customerId);
        }
        req.setAttribute("orders", orders);
        
        req.getRequestDispatcher("/OrderHistory.jsp").forward(req, resp);
    }
}
