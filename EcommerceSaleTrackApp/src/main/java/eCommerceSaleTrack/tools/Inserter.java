package eCommerceSaleTrack.tools;
import eCommerceSaleTrack.dal.*;
import eCommerceSaleTrack.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Inserter {
	
	public static void main(String[] args) throws SQLException {
        // DAO instance
        GeolocationDAO geolocationDao = GeolocationDAO.getInstance();

        // INSERT operation: Creating new geolocation entries
        Geolocation geo1 = new Geolocation("12345", 40.7128f, -74.0060f, "New York", "NY");
        geo1 = geolocationDao.create(geo1);
        System.out.println("Created geolocation: " + geo1.getGeolocationZipCodePrefix());

        Geolocation geo2 = new Geolocation("67890", 34.0522f, -118.2437f, "Los Angeles", "CA");
        geo2 = geolocationDao.create(geo2);
        System.out.println("Created geolocation: " + geo2.getGeolocationZipCodePrefix());

        // READ operation: Fetching geolocation by ZipCodePrefix
        String zipCodeToFetch = "12345";
        Geolocation fetchedGeo = geolocationDao.getGeolocationByZipCodePrefix(zipCodeToFetch);
        if (fetchedGeo != null) {
            System.out.format("Fetched geolocation: ZipCodePrefix=%s, City=%s, State=%s, Lat=%.4f, Lng=%.4f\n",
                fetchedGeo.getGeolocationZipCodePrefix(), fetchedGeo.getGeolocationCity(), 
                fetchedGeo.getGeolocationState(), fetchedGeo.getGeolocationLat(), 
                fetchedGeo.getGeolocationLng());
        } else {
            System.out.println("Geolocation not found with ZipCodePrefix: " + zipCodeToFetch);
        }

        // READ operation: Fetching all geolocations in a specific state
        String stateToFetch = "NY";
        List<Geolocation> geolocationsInState = geolocationDao.getGeolocationsByState(stateToFetch);
        System.out.println("Geolocations in " + stateToFetch + ":");
        for (Geolocation geo : geolocationsInState) {
            System.out.format(" - ZipCodePrefix=%s, City=%s, State=%s, Lat=%.4f, Lng=%.4f\n",
                geo.getGeolocationZipCodePrefix(), geo.getGeolocationCity(), 
                geo.getGeolocationState(), geo.getGeolocationLat(), 
                geo.getGeolocationLng());
        }

        // UPDATE operation: Updating geolocation city and state
        if (fetchedGeo != null) {
            Geolocation updatedGeo = geolocationDao.updateGeolocationCityAndState(fetchedGeo, "Buffalo", "NY");
            System.out.format("Updated geolocation: ZipCodePrefix=%s, New City=%s, New State=%s\n",
                updatedGeo.getGeolocationZipCodePrefix(), updatedGeo.getGeolocationCity(), 
                updatedGeo.getGeolocationState());
        }

        // DELETE operation: Deleting geolocation by ZipCodePrefix
        String zipCodeToDelete = "67890";
        Geolocation deletedGeo = geolocationDao.delete(geo2);
        System.out.println("Geolocation with ZipCodePrefix " + zipCodeToDelete + " deleted: " + (deletedGeo == null));

      // CustomerDao, orderItemsDao, orderPaymentsDao
      // DAO instances
      CustomersDAO customersDao = CustomersDAO.getInstance();
      OrderItemsDAO orderItemsDao = OrderItemsDAO.getInstance();
      OrderPaymentsDAO orderPaymentsDao = OrderPaymentsDAO.getInstance();

      // INSERT operation for Customers
      Customers customer1 = new Customers("cust123", "unique123", "10001", "New York", "NY");
      customer1 = customersDao.create(customer1);
      System.out.println("Created customer: " + customer1.getCustomerId());

      // READ operation for Customers by CustomerId
      Customers fetchedCustomer = customersDao.getCustomerById("cust123");
            if (fetchedCustomer != null) {
        System.out.format("Fetched customer: CustomerId=%s, City=%s, State=%s\n",
            fetchedCustomer.getCustomerId(), fetchedCustomer.getCustomerCity(), fetchedCustomer.getCustomerState());
      }

      // UPDATE operation for Customers
            if (fetchedCustomer != null) {
        Customers updatedCustomer = customersDao.updateCustomerCityAndState(fetchedCustomer, "Buffalo", "NY");
        System.out.format("Updated customer: CustomerId=%s, New City=%s, New State=%s\n",
            updatedCustomer.getCustomerId(), updatedCustomer.getCustomerCity(), updatedCustomer.getCustomerState());
      }

      // DELETE operation for Customers
      Customers deletedCustomer = customersDao.delete(customer1);
            System.out.println("Customer deleted: " + (deletedCustomer == null));

      // INSERT operation for OrderItems
      OrderItems orderItem1 = new OrderItems("order456", 1, "prod789", "seller123", new Date(), 29.99, 5.99);
      orderItem1 = orderItemsDao.create(orderItem1);
            System.out.println("Created order item: " + orderItem1.getOrderId());

      // READ operation for OrderItems by OrderId and OrderItemId
      OrderItems fetchedOrderItem = orderItemsDao.getOrderItemById("order456", 1);
            if (fetchedOrderItem != null) {
        System.out.format("Fetched order item: OrderId=%s, ProductId=%s, Price=%.2f\n",
            fetchedOrderItem.getOrderId(), fetchedOrderItem.getProductId(), fetchedOrderItem.getPrice());
      }

      // UPDATE operation for OrderItems
            if (fetchedOrderItem != null) {
        OrderItems updatedOrderItem = orderItemsDao.updatePriceAndFreightValue(fetchedOrderItem, 34.99, 6.99);
        System.out.format("Updated order item: OrderId=%s, New Price=%.2f, New Freight=%.2f\n",
            updatedOrderItem.getOrderId(), updatedOrderItem.getPrice(), updatedOrderItem.getFreightValue());
      }

      // DELETE operation for OrderItems
      OrderItems deletedOrderItem = orderItemsDao.delete(orderItem1);
            System.out.println("Order item deleted: " + (deletedOrderItem == null));

      // INSERT operation for OrderPayments
      OrderPayments orderPayment1 = new OrderPayments("order456", 1, OrderPayments.PaymentType.credit_card, 3, 99.99);
      orderPayment1 = orderPaymentsDao.create(orderPayment1);
            System.out.println("Created order payment: " + orderPayment1.getOrderId());

      // READ operation for OrderPayments by OrderId and PaymentSequential
      OrderPayments fetchedOrderPayment = orderPaymentsDao.getOrderPaymentById("order456", 1);
      if (fetchedOrderPayment != null) {
        System.out.format("Fetched order payment: OrderId=%s, PaymentType=%s, Amount=%.2f\n",
            fetchedOrderPayment.getOrderId(), fetchedOrderPayment.getPaymentType(), fetchedOrderPayment.getPaymentValue());
      }

      // UPDATE operation for OrderPayments
        if (fetchedOrderPayment != null) {
        OrderPayments updatedOrderPayment = orderPaymentsDao.updatePaymentTypeAndValue(fetchedOrderPayment, OrderPayments.PaymentType.debit_card, 89.99);
        System.out.format("Updated order payment: OrderId=%s, New Type=%s, New Amount=%.2f\n",
            updatedOrderPayment.getOrderId(), updatedOrderPayment.getPaymentType(), updatedOrderPayment.getPaymentValue());
      }

      // DELETE operation for OrderPayments
      OrderPayments deletedOrderPayment = orderPaymentsDao.delete(orderPayment1);
      System.out.println("Order payment deleted: " + (deletedOrderPayment == null));


      //orderReviewsDAO, OrdersDAO, ProductCategoryDAO
      // DAO instances
      OrderReviewsDAO orderReviewsDao = OrderReviewsDAO.getInstance();
      OrdersDAO ordersDao = OrdersDAO.getInstance();
      ProductCategoryDAO productCategoryDao = ProductCategoryDAO.getInstance();

      // INSERT operation for OrderReviews
      OrderReviews review1 = new OrderReviews(null, "order123", 5, "Excellent", "Great service", new Date(), new Date());
      review1 = orderReviewsDao.create(review1);
      System.out.println("Created order review: " + review1.getReviewId());

      // READ operation for OrderReviews by ReviewId
      OrderReviews fetchedReview = orderReviewsDao.getOrderReviewById(review1.getReviewId());
      if (fetchedReview != null) {
        System.out.format("Fetched order review: ReviewId=%s, Score=%d, Message=%s\n",
            fetchedReview.getReviewId(), fetchedReview.getReviewScore(), fetchedReview.getReviewCommentMessage());
      }

      // UPDATE operation for OrderReviews
      if (fetchedReview != null) {
        OrderReviews updatedReview = orderReviewsDao.updateReviewScoreAndComment(fetchedReview, 4, "Good service");
        System.out.format("Updated review: ReviewId=%s, New Score=%d, New Message=%s\n",
            updatedReview.getReviewId(), updatedReview.getReviewScore(), updatedReview.getReviewCommentMessage());
      }

      // DELETE operation for OrderReviews
      OrderReviews deletedReview = orderReviewsDao.delete(review1);
      System.out.println("Order review deleted: " + (deletedReview == null));

      // INSERT operation for Orders
      Orders order1 = new Orders("order456", "cust456", Orders.OrderStatus.delivered, new Date(), new Date(), new Date(), new Date(), new Date());
      order1 = ordersDao.create(order1);
      System.out.println("Created order: " + order1.getOrderId());

      // READ operation for Orders by OrderId
      Orders fetchedOrder = ordersDao.getOrderById("order456");
      if (fetchedOrder != null) {
        System.out.format("Fetched order: OrderId=%s, Status=%s\n",
            fetchedOrder.getOrderId(), fetchedOrder.getOrderStatus());
      }

      // UPDATE operation for Orders
      if (fetchedOrder != null) {
        Orders updatedOrder = ordersDao.updateOrderStatus(fetchedOrder, Orders.OrderStatus.shipped);
        System.out.format("Updated order: OrderId=%s, New Status=%s\n",
            updatedOrder.getOrderId(), updatedOrder.getOrderStatus());
      }

      // DELETE operation for Orders
      Orders deletedOrder = ordersDao.delete(order1);
      System.out.println("Order deleted: " + (deletedOrder == null));

      // INSERT operation for ProductCategory
      ProductCategory category1 = new ProductCategory("Electronics", "Electronics");
      category1 = productCategoryDao.create(category1);
      System.out.println("Created product category: " + category1.getProductCategoryName());

      // READ operation for ProductCategory by ProductCategoryName
      ProductCategory fetchedCategory = productCategoryDao.getProductCategoryByName("Electronics");
      if (fetchedCategory != null) {
        System.out.format("Fetched category: Name=%s, English Name=%s\n",
            fetchedCategory.getProductCategoryName(), fetchedCategory.getProductCategoryNameEnglish());
      }

      // UPDATE operation for ProductCategory
      if (fetchedCategory != null) {
        ProductCategory updatedCategory = productCategoryDao.updateProductCategoryNameEnglish(fetchedCategory, "Gadgets");
        System.out.format("Updated category: Name=%s, New English Name=%s\n",
            updatedCategory.getProductCategoryName(), updatedCategory.getProductCategoryNameEnglish());
      }

      // DELETE operation for ProductCategory
      ProductCategory deletedCategory = productCategoryDao.delete(category1);
      System.out.println("Product category deleted: " + (deletedCategory == null));


      // ProductsDAO, SellersDAO, ShippingDAL
    // DAO instances
    ProductsDAO productsDao = ProductsDAO.getInstance();
    SellersDAO sellersDao = SellersDAO.getInstance();
    ShippingDAO shippingDao = ShippingDAO.getInstance();

    // INSERT operation for Products
    Products product1 = new Products("prod123", "Electronics", 10, 30, 5, 1000, 15, 10, 5);
    product1 = productsDao.create(product1);
    System.out.println("Created product: " + product1.getProductId());

    // READ operation for Products by ProductId
    Products fetchedProduct = productsDao.getProductById("prod123");
    if (fetchedProduct != null) {
      System.out.format("Fetched product: ProductId=%s, Category=%s, Weight=%d grams\n",
          fetchedProduct.getProductId(), fetchedProduct.getProductCategoryName(), fetchedProduct.getProductWeightG());
    }

    // UPDATE operation for Products
    if (fetchedProduct != null) {
      Products updatedProduct = productsDao.updateProductDetails(fetchedProduct, "Gadgets", 12, 35, 6, 1200, 16, 11, 6);
      System.out.format("Updated product: ProductId=%s, New Category=%s, New Weight=%d grams\n",
          updatedProduct.getProductId(), updatedProduct.getProductCategoryName(), updatedProduct.getProductWeightG());
    }

    // DELETE operation for Products
    Products deletedProduct = productsDao.delete(product1);
    System.out.println("Product deleted: " + (deletedProduct == null));

    // INSERT operation for Sellers
    Sellers seller1 = new Sellers("seller456", "12345", "Seattle", "WA");
    seller1 = sellersDao.create(seller1);
    System.out.println("Created seller: " + seller1.getSellerId());

    // READ operation for Sellers by SellerId
    Sellers fetchedSeller = sellersDao.getSellerById("seller456");
    if (fetchedSeller != null) {
      System.out.format("Fetched seller: SellerId=%s, City=%s, State=%s\n",
          fetchedSeller.getSellerId(), fetchedSeller.getSellerCity(), fetchedSeller.getSellerState());
    }

    // UPDATE operation for Sellers
    if (fetchedSeller != null) {
      Sellers updatedSeller = sellersDao.updateSellerCityAndState(fetchedSeller, "Bellevue", "WA");
      System.out.format("Updated seller: SellerId=%s, New City=%s, New State=%s\n",
          updatedSeller.getSellerId(), updatedSeller.getSellerCity(), updatedSeller.getSellerState());
    }

    // DELETE operation for Sellers
    Sellers deletedSeller = sellersDao.delete(seller1);
    System.out.println("Seller deleted: " + (deletedSeller == null));

    // INSERT operation for Shipping
    Shipping shipping1 = new Shipping("order789", "cust789", "54321", "CA", 50.0, 500.0, 550.0, new Date());
    shipping1 = shippingDao.create(shipping1);
    System.out.println("Created shipping record with ShippingId: " + shipping1.getShippingId());

    // READ operation for Shipping by ShippingId
    Shipping fetchedShipping = shippingDao.getShippingById(shipping1.getShippingId());
    if (fetchedShipping != null) {
      System.out.format("Fetched shipping record: ShippingId=%d, State=%s, TotalOrderValue=%.2f\n",
          fetchedShipping.getShippingId(), fetchedShipping.getState(), fetchedShipping.getTotalOrderValue());
    }

    // UPDATE operation for Shipping
    if (fetchedShipping != null) {
      Shipping updatedShipping = shippingDao.updateState(fetchedShipping, "NY");
      System.out.format("Updated shipping record: ShippingId=%d, New State=%s\n",
          updatedShipping.getShippingId(), updatedShipping.getState());
    }

    // DELETE operation for Shipping
    Shipping deletedShipping = shippingDao.delete(shipping1);
    System.out.println("Shipping record deleted: " + (deletedShipping == null));

  }
}
