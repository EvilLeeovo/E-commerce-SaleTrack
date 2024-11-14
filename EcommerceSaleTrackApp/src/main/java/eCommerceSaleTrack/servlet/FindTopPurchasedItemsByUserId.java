package eCommerceSaleTrack.servlet;

import eCommerceSaleTrack.dal.OrdersDAO;
import eCommerceSaleTrack.dal.ProductsDAO;
import eCommerceSaleTrack.model.Products;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findTopPurchasedItemsByUserId")
public class FindTopPurchasedItemsByUserId extends HttpServlet {
    private OrdersDAO ordersDAO;
    private ProductsDAO productDAO;

    @Override
    public void init() throws ServletException {
        ordersDAO = OrdersDAO.getInstance();
        productDAO = ProductsDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        if (userId == null || userId.trim().isEmpty()) {
            request.setAttribute("error", "User ID is required.");
            request.getRequestDispatcher("/findTopPurchasedItemsByUserId.jsp").forward(request, response);
            return;
        }

        try {
            List<Products> topPurchasedProducts = productDAO.findTopPurchasedItemsByUserId(userId);
            request.setAttribute("topPurchasedProducts", topPurchasedProducts);
            request.setAttribute("userId", userId);
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while retrieving top purchased items: " + e.getMessage());
        }

        request.getRequestDispatcher("/findTopPurchasedItemsByUserId.jsp").forward(request, response);
    }
}
