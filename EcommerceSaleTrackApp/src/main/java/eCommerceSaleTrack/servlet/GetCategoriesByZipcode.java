package eCommerceSaleTrack.servlet;

import eCommerceSaleTrack.dal.ProductCategoryDAO;
import eCommerceSaleTrack.model.ZipcodeCategoryDTO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/getCategoriesByZipcode")
public class GetCategoriesByZipcode extends HttpServlet {
  
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

        List<ZipcodeCategoryDTO> categoryByZipcode = new ArrayList<>();

        String zipcode = req.getParameter("zipcode");
        if (zipcode == null || zipcode.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ZIP code.");
        } else {
            try {
                categoryByZipcode = productCategoryDAO.getCategoriesByZipcode(zipcode);
                messages.put("success", "Displaying categories for ZIP code: " + zipcode);
                messages.put("previousZipcode", zipcode);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.setAttribute("categoryByZipcode", categoryByZipcode);

        req.getRequestDispatcher("/CategoriesByZipcode.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
