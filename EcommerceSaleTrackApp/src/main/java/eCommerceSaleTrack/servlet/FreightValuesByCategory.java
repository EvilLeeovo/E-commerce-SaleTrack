package eCommerceSaleTrack.servlet;

import eCommerceSaleTrack.dal.ProductCategoryDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/freightValuesByCategory")
public class FreightValuesByCategory extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductCategoryDAO productCategoryDAO;

    @Override
    public void init() throws ServletException {
        productCategoryDAO = ProductCategoryDAO.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        String categoryName = req.getParameter("categoryName");
        if (categoryName == null || categoryName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid product category.");
        } else {
            try {
                Double totalFreightValue = productCategoryDAO.getTotalFreightValueByCategory(categoryName);
                if (totalFreightValue != null) {
                    req.setAttribute("totalFreightValue", totalFreightValue);
                    messages.put("success", "Displaying total Freight Value for category: " + categoryName);
                } else {
                    messages.put("success", "No freight data found for category: " + categoryName);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/FreightValuesByCategory.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp); // Redirect POST requests to GET for simplicity.
    }
}
