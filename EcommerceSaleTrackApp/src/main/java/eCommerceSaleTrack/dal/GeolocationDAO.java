package eCommerceSaleTrack.dal;

import eCommerceSaleTrack.model.Geolocation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeolocationDAO {
    protected ConnectionManager connectionManager;

    // Singleton pattern: limit instantiation to one object.
    private static GeolocationDAO instance = null;

    protected GeolocationDAO() {
        connectionManager = new ConnectionManager();
    }

    public static GeolocationDAO getInstance() {
        if (instance == null) {
            instance = new GeolocationDAO();
        }
        return instance;
    }

    /**
     * Save a new geolocation record to the database.
     */
    public Geolocation create(Geolocation geolocation) throws SQLException {
        String insertGeolocation = "INSERT INTO Geolocation(GeolocationZipCodePrefix, GeolocationLat, GeolocationLng, GeolocationCity, GeolocationState) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertGeolocation, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, geolocation.getGeolocationZipCodePrefix());
            insertStmt.setFloat(2, geolocation.getGeolocationLat());
            insertStmt.setFloat(3, geolocation.getGeolocationLng());
            insertStmt.setString(4, geolocation.getGeolocationCity());
            insertStmt.setString(5, geolocation.getGeolocationState());
            insertStmt.executeUpdate();
            
            return geolocation;
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
     * Get a geolocation record by ZipCodePrefix.
     */
    public Geolocation getGeolocationByZipCodePrefix(String zipCodePrefix) throws SQLException {
        String selectGeolocation = "SELECT GeolocationZipCodePrefix, GeolocationLat, GeolocationLng, GeolocationCity, GeolocationState FROM Geolocation WHERE GeolocationZipCodePrefix=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectGeolocation);
            selectStmt.setString(1, zipCodePrefix);
            results = selectStmt.executeQuery();

            if (results.next()) {
                String geolocationZipCodePrefix = results.getString("GeolocationZipCodePrefix");
                float geolocationLat = results.getFloat("GeolocationLat");
                float geolocationLng = results.getFloat("GeolocationLng");
                String geolocationCity = results.getString("GeolocationCity");
                String geolocationState = results.getString("GeolocationState");

                Geolocation geolocation = new Geolocation(geolocationZipCodePrefix, geolocationLat, geolocationLng, geolocationCity, geolocationState);
                return geolocation;
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
     * Update the city and state of a geolocation record based on ZipCodePrefix.
     */
    public Geolocation updateGeolocationCityAndState(Geolocation geolocation, String newCity, String newState) throws SQLException {
        String updateGeolocation = "UPDATE Geolocation SET GeolocationCity=?, GeolocationState=? WHERE GeolocationZipCodePrefix=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateGeolocation);
            updateStmt.setString(1, newCity);
            updateStmt.setString(2, newState);
            updateStmt.setString(3, geolocation.getGeolocationZipCodePrefix());
            updateStmt.executeUpdate();

            // Update the geolocation object before returning.
            geolocation.setGeolocationCity(newCity);
            geolocation.setGeolocationState(newState);
            return geolocation;
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
     * Delete a geolocation record by ZipCodePrefix.
     */
    public Geolocation delete(Geolocation geolocation) throws SQLException {
        String deleteGeolocation = "DELETE FROM Geolocation WHERE GeolocationZipCodePrefix=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteGeolocation);
            deleteStmt.setString(1, geolocation.getGeolocationZipCodePrefix());
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
     * Get all geolocation records in a specific state.
     */
    public List<Geolocation> getGeolocationsByState(String state) throws SQLException {
        List<Geolocation> geolocations = new ArrayList<>();
        String selectGeolocations = "SELECT GeolocationZipCodePrefix, GeolocationLat, GeolocationLng, GeolocationCity, GeolocationState FROM Geolocation WHERE GeolocationState=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectGeolocations);
            selectStmt.setString(1, state);
            results = selectStmt.executeQuery();

            while (results.next()) {
                String geolocationZipCodePrefix = results.getString("GeolocationZipCodePrefix");
                float geolocationLat = results.getFloat("GeolocationLat");
                float geolocationLng = results.getFloat("GeolocationLng");
                String geolocationCity = results.getString("GeolocationCity");
                String geolocationState = results.getString("GeolocationState");

                Geolocation geolocation = new Geolocation(geolocationZipCodePrefix, geolocationLat, geolocationLng, geolocationCity, geolocationState);
                geolocations.add(geolocation);
            }
            return geolocations;
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
