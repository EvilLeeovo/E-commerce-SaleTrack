package eCommerceSaleTrack.model;

public class Geolocation {
	protected String geolocationZipCodePrefix;
	protected float geolocationLat;
	protected float geolocationLng;
	protected String geolocationCity;
	protected String geolocationState;
	
	/**
	 * Construct a Geolocation instance, with input arguments:
	 * @param geolocationZipCodePrefix is zip code
	 * @param geolocationLat is latitude
	 * @param geolocationLng is longitude
	 * @param geolocationCity is city
	 * @param geolocationState is state
	 */
	public Geolocation(String geolocationZipCodePrefix, float geolocationLat, float geolocationLng,
			String geolocationCity, String geolocationState) {
		super();
		this.geolocationZipCodePrefix = geolocationZipCodePrefix;
		this.geolocationLat = geolocationLat;
		this.geolocationLng = geolocationLng;
		this.geolocationCity = geolocationCity;
		this.geolocationState = geolocationState;
	}

	public String getGeolocationZipCodePrefix() {
		return geolocationZipCodePrefix;
	}

	public void setGeolocationZipCodePrefix(String geolocationZipCodePrefix) {
		this.geolocationZipCodePrefix = geolocationZipCodePrefix;
	}

	public float getGeolocationLat() {
		return geolocationLat;
	}

	public void setGeolocationLat(float geolocationLat) {
		this.geolocationLat = geolocationLat;
	}

	public float getGeolocationLng() {
		return geolocationLng;
	}

	public void setGeolocationLng(float geolocationLng) {
		this.geolocationLng = geolocationLng;
	}

	public String getGeolocationCity() {
		return geolocationCity;
	}

	public void setGeolocationCity(String geolocationCity) {
		this.geolocationCity = geolocationCity;
	}

	public String getGeolocationState() {
		return geolocationState;
	}

	public void setGeolocationState(String geolocationState) {
		this.geolocationState = geolocationState;
	}
	
	
}
