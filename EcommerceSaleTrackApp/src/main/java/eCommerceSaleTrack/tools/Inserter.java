package eCommerceSaleTrack.tools;

import eCommerceSaleTrack.dal.*;
import eCommerceSaleTrack.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Inserter {
    
    public static void main(String[] args) throws SQLException {
        // DAO instances
        GeolocationDAO geolocationDao = GeolocationDAO.getInstance();
        CustomersDAO customersDao = CustomersDAO.getInstance();
        SellersDAO sellersDao = SellersDAO.getInstance();
        OrdersDAO ordersDao = OrdersDAO.getInstance();
        ProductsDAO productsDao = ProductsDAO.getInstance();
        OrderItemsDAO orderItemsDao = OrderItemsDAO.getInstance();
        OrderPaymentsDAO orderPaymentsDao = OrderPaymentsDAO.getInstance();
        OrderReviewsDAO orderReviewsDao = OrderReviewsDAO.getInstance();
        ProductCategoryDAO productCategoryDao = ProductCategoryDAO.getInstance();
        ShippingDAO shippingDao = ShippingDAO.getInstance();
        
        // INSERT operations to satisfy FK dependencies
        
        // 1. Create Geolocation entries (needed by Customers and Sellers)
        Geolocation geo1 = new Geolocation("12345", 40.7128f, -74.0060f, "New York", "NY");
        geo1 = geolocationDao.create(geo1);
        System.out.println("Created geolocation: " + geo1.getGeolocationZipCodePrefix());

        Geolocation geo2 = new Geolocation("67890", 34.0522f, -118.2437f, "Los Angeles", "CA");
        geo2 = geolocationDao.create(geo2);
        System.out.println("Created geolocation: " + geo2.getGeolocationZipCodePrefix());

        // 2. Create Customer using existing Geolocation
        Customers customer1 = new Customers("cust123", "unique123", "12345", "New York", "NY");
        customer1 = customersDao.create(customer1);
        System.out.println("Created customer: " + customer1.getCustomerId());

        // 3. Create Seller using existing Geolocation
        Sellers seller1 = new Sellers("seller123", "12345", "New York", "NY");
        seller1 = sellersDao.create(seller1);
        System.out.println("Created seller: " + seller1.getSellerId());

        // 4. Create ProductCategory (needed by Products)
        ProductCategory category1 = new ProductCategory("Electronics", "Electronics");
        category1 = productCategoryDao.create(category1);
        System.out.println("Created product category: " + category1.getProductCategoryName());

        // 5. Create Product using existing ProductCategory
        Products product1 = new Products("prod123", "Electronics", 10, 30, 5, 1000, 15, 10, 5);
        product1 = productsDao.create(product1);
        System.out.println("Created product: " + product1.getProductId());

        // 6. Create Order using existing Customer
        Orders order1 = new Orders("order123", "cust123", Orders.OrderStatus.delivered, new Date(), new Date(), new Date(), new Date(), new Date());
        order1 = ordersDao.create(order1);
        System.out.println("Created order: " + order1.getOrderId());

        // 7. Create OrderItem using existing Order, Product, and Seller
        OrderItems orderItem1 = new OrderItems("order123", 1, "prod123", "seller123", new Date(), 29.99, 5.99);
        orderItem1 = orderItemsDao.create(orderItem1);
        System.out.println("Created order item: " + orderItem1.getOrderId());

        // 8. Create OrderPayment using existing Order
        OrderPayments orderPayment1 = new OrderPayments("order123", 1, OrderPayments.PaymentType.credit_card, 3, 99.99);
        orderPayment1 = orderPaymentsDao.create(orderPayment1);
        System.out.println("Created order payment: " + orderPayment1.getOrderId());

        // 9. Create OrderReview using existing Order
        OrderReviews review1 = new OrderReviews("review123", "order123", 5, "Excellent", "Great service", new Date(), new Date());
        review1 = orderReviewsDao.create(review1);
        System.out.println("Created order review: " + review1.getReviewId());

        // 10. Create Shipping record using existing Order and Customer
        Shipping shipping1 = new Shipping("order123", "cust123", "12345", "NY", 50.0, 500.0, 550.0, new Date());
        shipping1 = shippingDao.create(shipping1);
        System.out.println("Created shipping record with ShippingId: " + shipping1.getShippingId());

        // READ, UPDATE, and DELETE operations

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

        // Additional READ, UPDATE, DELETE operations for Customers, OrderItems, OrderPayments, etc.

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

        // READ, UPDATE, DELETE operations for OrderItems
        OrderItems fetchedOrderItem = orderItemsDao.getOrderItemById("order123", 1);
        if (fetchedOrderItem != null) {
            System.out.format("Fetched order item: OrderId=%s, ProductId=%s, Price=%.2f\n",
                fetchedOrderItem.getOrderId(), fetchedOrderItem.getProductId(), fetchedOrderItem.getPrice());
        }

        if (fetchedOrderItem != null) {
            OrderItems updatedOrderItem = orderItemsDao.updatePriceAndFreightValue(fetchedOrderItem, 34.99, 6.99);
            System.out.format("Updated order item: OrderId=%s, New Price=%.2f, New Freight=%.2f\n",
                updatedOrderItem.getOrderId(), updatedOrderItem.getPrice(), updatedOrderItem.getFreightValue());
        }

        OrderItems deletedOrderItem = orderItemsDao.delete(orderItem1);
        System.out.println("Order item deleted: " + (deletedOrderItem == null));

        // READ, UPDATE, DELETE operations for OrderPayments
        OrderPayments fetchedOrderPayment = orderPaymentsDao.getOrderPaymentById("order123", 1);
        if (fetchedOrderPayment != null) {
            System.out.format("Fetched order payment: OrderId=%s, PaymentType=%s, Amount=%.2f\n",
                fetchedOrderPayment.getOrderId(), fetchedOrderPayment.getPaymentType(), fetchedOrderPayment.getPaymentValue());
        }

        if (fetchedOrderPayment != null) {
            OrderPayments updatedOrderPayment = orderPaymentsDao.updatePaymentTypeAndValue(fetchedOrderPayment, OrderPayments.PaymentType.debit_card, 89.99);
            System.out.format("Updated order payment: OrderId=%s, New Type=%s, New Amount=%.2f\n",
                updatedOrderPayment.getOrderId(), updatedOrderPayment.getPaymentType(), updatedOrderPayment.getPaymentValue());
        }

        OrderPayments deletedOrderPayment = orderPaymentsDao.delete(orderPayment1);
        System.out.println("Order payment deleted: " + (deletedOrderPayment == null));

        System.out.println("All done");
    }
}
