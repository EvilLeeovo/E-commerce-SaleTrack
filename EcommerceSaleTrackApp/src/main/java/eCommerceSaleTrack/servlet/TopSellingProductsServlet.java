package eCommerceSaleTrack.servlet;

import eCommerceSaleTrack.dal.ProductsDAO;
import eCommerceSaleTrack.model.Products;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/TopSellingProducts")
public class TopSellingProductsServlet extends HttpServlet {
    private ProductsDAO productsDAO;

    @Override
    public void init() throws ServletException {
        productsDAO = ProductsDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("categoryName");

        if (categoryName == null || categoryName.trim().isEmpty()) {
            req.setAttribute("error", "Category name is required.");
            req.getRequestDispatcher("/TopSellingProducts.jsp").forward(req, resp);
            return;
        }

        try {
            List<Products> products = productsDAO.getTopSellingProductsByCategory(categoryName);
            req.setAttribute("products", products);
        } catch (Exception e) {
            req.setAttribute("error", "An error occurred while fetching products.");
            e.printStackTrace();
        }

        req.getRequestDispatcher("/TopSellingProducts.jsp").forward(req, resp);
    }
}
