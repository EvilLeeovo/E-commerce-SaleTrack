package eCommerceSaleTrack.dal;

import eCommerceSaleTrack.model.OrderReviews;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderReviewsDAO {
    protected ConnectionManager connectionManager;

    // Singleton pattern: restricts instantiation to one object.
    private static OrderReviewsDAO instance = null;

    protected OrderReviewsDAO() {
        connectionManager = new ConnectionManager();
    }

    public static OrderReviewsDAO getInstance() {
        if (instance == null) {
            instance = new OrderReviewsDAO();
        }
        return instance;
    }

    public OrderReviews create(OrderReviews orderReview) throws SQLException {
        String insertOrderReview = "INSERT INTO OrderReviews(ReviewId, OrderId, ReviewScore, ReviewCommentTitle, "
                + "ReviewCommentMessage, ReviewCreationDate, ReviewAnswerTimestamp) VALUES(?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertOrderReview);
            
            // Set the parameters for the insert statement, including manually setting ReviewId
            insertStmt.setString(1, orderReview.getReviewId()); // Manually provided ReviewId
            insertStmt.setString(2, orderReview.getOrderId());
            insertStmt.setInt(3, orderReview.getReviewScore());
            insertStmt.setString(4, orderReview.getReviewCommentTitle());
            insertStmt.setString(5, orderReview.getReviewCommentMessage());
            insertStmt.setDate(6, new Date(orderReview.getReviewCreationDate().getTime()));
            insertStmt.setDate(7, new Date(orderReview.getReviewAnswerTimestamp().getTime()));
            // Execute the insert statement
            insertStmt.executeUpdate();

            return orderReview;
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    
    /**
     * Save a new OrderReview to the database.
     */
    public OrderReviews createwithoutid(OrderReviews orderReview) throws SQLException {
        String insertOrderReview = "INSERT INTO OrderReviews(OrderId, ReviewScore, ReviewCommentTitle, ReviewCommentMessage, ReviewCreationDate, ReviewAnswerTimestamp) VALUES(?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertOrderReview, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, orderReview.getOrderId());
            insertStmt.setInt(2, orderReview.getReviewScore());
            insertStmt.setString(3, orderReview.getReviewCommentTitle());
            insertStmt.setString(4, orderReview.getReviewCommentMessage());
            insertStmt.setDate(5, new Date(orderReview.getReviewCreationDate().getTime()));
            insertStmt.setDate(6, new Date(orderReview.getReviewAnswerTimestamp().getTime()));
            insertStmt.executeUpdate();

            // Retrieve the auto-generated ReviewId and set it on the orderReview object.
            ResultSet resultKeys = insertStmt.getGeneratedKeys();
            if (resultKeys.next()) {
                String reviewId = resultKeys.getString(1);
                orderReview.setReviewId(reviewId);
            }

            return orderReview;
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Get an OrderReview by ReviewId.
     */
    public OrderReviews getOrderReviewById(String reviewId) throws SQLException {
        String selectOrderReview = "SELECT ReviewId, OrderId, ReviewScore, ReviewCommentTitle, ReviewCommentMessage, ReviewCreationDate, ReviewAnswerTimestamp FROM OrderReviews WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectOrderReview);
            selectStmt.setString(1, reviewId);
            results = selectStmt.executeQuery();

            if (results.next()) {
                String orderId = results.getString("OrderId");
                int reviewScore = results.getInt("ReviewScore");
                String reviewCommentTitle = results.getString("ReviewCommentTitle");
                String reviewCommentMessage = results.getString("ReviewCommentMessage");
                Date reviewCreationDate = results.getDate("ReviewCreationDate");
                Date reviewAnswerTimestamp = results.getDate("ReviewAnswerTimestamp");

                OrderReviews orderReview = new OrderReviews(reviewId, orderId, reviewScore, reviewCommentTitle, reviewCommentMessage, reviewCreationDate, reviewAnswerTimestamp);
                return orderReview;
            }
        } finally {
            if (results != null) {
                results.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    /**
     * Update the review score and comment message of an OrderReview.
     */
    public OrderReviews updateReviewScoreAndComment(OrderReviews orderReview, int newReviewScore, String newCommentMessage) throws SQLException {
        String updateOrderReview = "UPDATE OrderReviews SET ReviewScore=?, ReviewCommentMessage=? WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateOrderReview);
            updateStmt.setInt(1, newReviewScore);
            updateStmt.setString(2, newCommentMessage);
            updateStmt.setString(3, orderReview.getReviewId());
            updateStmt.executeUpdate();

            // Update the orderReview instance before returning.
            orderReview.setReviewScore(newReviewScore);
            orderReview.setReviewCommentMessage(newCommentMessage);
            return orderReview;
        } finally {
            if (updateStmt != null) {
                updateStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Delete an OrderReview by ReviewId.
     */
    public OrderReviews delete(OrderReviews orderReview) throws SQLException {
        String deleteOrderReview = "DELETE FROM OrderReviews WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteOrderReview);
            deleteStmt.setString(1, orderReview.getReviewId());
            deleteStmt.executeUpdate();

            // Return null to indicate the record has been deleted.
            return null;
        } finally {
            if (deleteStmt != null) {
                deleteStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Get all OrderReviews by a specific OrderId.
     */
    public List<OrderReviews> getOrderReviewsByOrderId(String orderId) throws SQLException {
        List<OrderReviews> orderReviewsList = new ArrayList<>();
        String selectOrderReviews = "SELECT ReviewId, OrderId, ReviewScore, ReviewCommentTitle, ReviewCommentMessage, ReviewCreationDate, ReviewAnswerTimestamp FROM OrderReviews WHERE OrderId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectOrderReviews);
            selectStmt.setString(1, orderId);
            results = selectStmt.executeQuery();

            while (results.next()) {
                String reviewId = results.getString("ReviewId");
                int reviewScore = results.getInt("ReviewScore");
                String reviewCommentTitle = results.getString("ReviewCommentTitle");
                String reviewCommentMessage = results.getString("ReviewCommentMessage");
                Date reviewCreationDate = results.getDate("ReviewCreationDate");
                Date reviewAnswerTimestamp = results.getDate("ReviewAnswerTimestamp");

                OrderReviews orderReview = new OrderReviews(reviewId, orderId, reviewScore, reviewCommentTitle, reviewCommentMessage, reviewCreationDate, reviewAnswerTimestamp);
                orderReviewsList.add(orderReview);
            }
            return orderReviewsList;
        } finally {
            if (results != null) {
                results.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
