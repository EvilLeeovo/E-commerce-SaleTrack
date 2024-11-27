CREATE SCHEMA IF NOT EXISTS OrderApplication;
USE OrderApplication;

DROP TABLE IF EXISTS Shipping;
DROP TABLE IF EXISTS OrderPayments;
DROP TABLE IF EXISTS OrderItems;
DROP TABLE IF EXISTS OrderReviews;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS ProductCategory;
DROP TABLE IF EXISTS Sellers;
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Geolocation;

CREATE TABLE Geolocation (
    GeolocationZipCodePrefix VARCHAR(255),
    GeolocationLat FLOAT,
    GeolocationLng FLOAT,
    GeolocationCity VARCHAR(255),
    GeolocationState VARCHAR(255),
    CONSTRAINT pk_Geolocation_ZipCodePrefix PRIMARY KEY (GeolocationZipCodePrefix)
);


CREATE TABLE Customers (
  CustomerId VARCHAR(255),
  CustomerUniqueId VARCHAR(255),
  CustomerZipCodePrefix VARCHAR(255),
  CustomerCity VARCHAR(255),
  CustomerState VARCHAR(255),
  CONSTRAINT pk_Customers_CustomerId PRIMARY KEY (CustomerId),
  CONSTRAINT fk_Customers_ZipCodePrefix FOREIGN KEY (CustomerZipCodePrefix)
    REFERENCES Geolocation(GeolocationZipCodePrefix)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Sellers (
  SellerId VARCHAR(255),
  SellerZipCodePrefix VARCHAR(255),
  SellerCity VARCHAR(255),
  SellerState VARCHAR(255),
  CONSTRAINT pk_Sellers_SellerId PRIMARY KEY (SellerId),
  CONSTRAINT fk_Sellers_ZipCodePrefix FOREIGN KEY (SellerZipCodePrefix)
    REFERENCES Geolocation(GeolocationZipCodePrefix)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE ProductCategory (
  ProductCategoryName VARCHAR(255),
  ProductCategoryNameEnglish VARCHAR(255),
  CONSTRAINT pk_ProductCategory_ProductCategoryName PRIMARY KEY (ProductCategoryName)
);

CREATE TABLE Products (
  ProductId VARCHAR(255),
  ProductCategoryName VARCHAR(255),
  ProductNameLength INT,
  ProductDescriptionLength INT,
  ProductPhotosQty INT,
  ProductWeightG INT,
  ProductLengthCm INT,
  ProductHeightCm INT,
  ProductWidthCm INT,
  CONSTRAINT pk_Products_ProductId PRIMARY KEY (ProductId),
  CONSTRAINT fk_Products_ProductCategory FOREIGN KEY (ProductCategoryName)
    REFERENCES ProductCategory(ProductCategoryName)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Orders (
  OrderId VARCHAR(255),
  CustomerId VARCHAR(255),
  OrderStatus ENUM('delivered', 'shipped', 'canceled', 'unavailable', 'invoiced') NOT NULL,
  OrderPurchaseTimestamp DATETIME,
  OrderApprovedAt DATETIME,
  OrderDeliveredCarrierDate DATETIME,
  OrderDeliveredCustomerDate DATETIME,
  OrderEstimatedDeliveryDate DATETIME,
  CONSTRAINT pk_Orders_OrderId PRIMARY KEY (OrderId),
  CONSTRAINT fk_Orders_CustomerId FOREIGN KEY (CustomerId)
    REFERENCES Customers(CustomerId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE OrderReviews (
  ReviewId VARCHAR(255),
  OrderId VARCHAR(255),
  ReviewScore INT,
  ReviewCommentTitle VARCHAR(1024),
  ReviewCommentMessage VARCHAR(1024),
  ReviewCreationDate DATETIME,
  ReviewAnswerTimestamp DATETIME,
  CONSTRAINT pk_OrderReviews_ReviewId PRIMARY KEY (ReviewId),
  CONSTRAINT fk_OrderReviews_OrderId FOREIGN KEY (OrderId)
    REFERENCES Orders(OrderId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE OrderItems (
  OrderId VARCHAR(255),
  OrderItemId INT,
  ProductId VARCHAR(255),
  SellerId VARCHAR(255),
  ShippingLimitDate DATETIME,
  Price DECIMAL(10,2),
  FreightValue DECIMAL(10,2),
  PRIMARY KEY (OrderId, OrderItemId),
  CONSTRAINT fk_OrderItems_OrderId FOREIGN KEY (OrderId)
    REFERENCES Orders(OrderId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_OrderItems_ProductId FOREIGN KEY (ProductId)
    REFERENCES Products(ProductId)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_OrderItems_SellerId FOREIGN KEY (SellerId)
    REFERENCES Sellers(SellerId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE OrderPayments (
  OrderId VARCHAR(255),
  PaymentSequential INT,
  PaymentType ENUM('credit_card', 'boleto', 'voucher', 'debit_card', 'not_defined'),
  PaymentInstallments INT,
  PaymentValue DECIMAL(10,2),
  PRIMARY KEY (OrderId, PaymentSequential),
  CONSTRAINT fk_OrderPayments_OrderId FOREIGN KEY (OrderId)
    REFERENCES Orders(OrderId)
    ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Shipping (
  ShippingId INT AUTO_INCREMENT, 
  OrderId VARCHAR(255), 
  CustomerId VARCHAR(255), 
  ZipCodePrefix VARCHAR(255), 
  State VARCHAR(255),
  TotalFreightValue DECIMAL(10,2),
  TotalItemValue DECIMAL(10,2),
  TotalOrderValue DECIMAL(10,2),
  OrderEstimatedDeliveryDate DATETIME,
  PRIMARY KEY (ShippingId),
  CONSTRAINT fk_Shipping_OrderId FOREIGN KEY (OrderId) 
    REFERENCES Orders(OrderId) 
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_Shipping_CustomerId FOREIGN KEY (CustomerId) 
    REFERENCES Customers(CustomerId) 
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Shipping_ZipCodePrefix FOREIGN KEY (ZipCodePrefix) 
    REFERENCES Geolocation(GeolocationZipCodePrefix) 
    ON UPDATE CASCADE ON DELETE SET NULL
);
