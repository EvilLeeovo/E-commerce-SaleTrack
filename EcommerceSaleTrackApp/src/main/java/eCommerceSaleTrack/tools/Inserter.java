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
    }
}
