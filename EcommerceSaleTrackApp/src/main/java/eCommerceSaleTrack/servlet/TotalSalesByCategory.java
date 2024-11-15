@WebServlet("/totalSalesByCategory")
public class TotalSalesByCategory extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private ProductCategoryDAO productCategoryDAO;

  @Override
  public void init() throws ServletException {
    productCategoryDAO = ProductCategoryDAO.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    try {
      Map<String, Double> totalSalesByCategory = productCategoryDAO.getTotalSalesByCategory();
      req.setAttribute("totalSalesByCategory", totalSalesByCategory);
      messages.put("success", "Displaying total sales by product category.");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    req.getRequestDispatcher("/TotalSalesByCategory.jsp").forward(req, resp);
  }
}
