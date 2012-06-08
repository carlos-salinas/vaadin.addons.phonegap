package com.thingtrack.com.vaddin.addon.phonegap;

public interface GeoLocationListener {

	public void onSuccess(Position position);
	
	public void onFailure(PositionError error);


	public class Position{
		
		private long timeStamp;
		private double accuracy;
		private double altitude;
		private double altitudeAccurency;
		private double heading;
		private double latitude;
		private double longitude;
		private double speed;
		
		public Position(long timeStamp, double accuracy, double altitude,
				double altitudeAccurency, double heading, double latitude,
				double longitude, double speed) {
			super();
			this.timeStamp = timeStamp;
			this.accuracy = accuracy;
			this.altitude = altitude;
			this.altitudeAccurency = altitudeAccurency;
			this.heading = heading;
			this.latitude = latitude;
			this.longitude = longitude;
			this.speed = speed;
		}
		
		public long getTimeStamp() {
			return timeStamp;
		}
		public double getAccuracy() {
			return accuracy;
		}
		public double getAltitude() {
			return altitude;
		}
		public double getAltitudeAccurency() {
			return altitudeAccurency;
		}
		public double getHeading() {
			return heading;
		}
		public double getLatitude() {
			return latitude;
		}
		public double getLongitude() {
			return longitude;
		}
		public double getSpeed() {
			return speed;
		}
		public void setTimeStamp(long timeStamp) {
			this.timeStamp = timeStamp;
		}
		public void setAccuracy(double accuracy) {
			this.accuracy = accuracy;
		}
		public void setAltitude(double altitude) {
			this.altitude = altitude;
		}
		public void setAltitudeAccurency(double altitudeAccurency) {
			this.altitudeAccurency = altitudeAccurency;
		}
		public void setHeading(double heading) {
			this.heading = heading;
		}
		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}
		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}
		public void setSpeed(double speed) {
			this.speed = speed;
		}
		
	}
	
	
	public enum errorType {
		
		PERMISSION_DENIED,
		POSITION_UNAVAILABLE,
		TIMEOUT
	}
	
	public class PositionError{
		
		private errorType type;
		private String message;
		
		public PositionError(errorType type, String message) {
			super();
			this.type = type;
			this.message = message;
		}

		public errorType getType() {
			return type;
		}

		public String getMessage() {
			return message;
		}
		
	}

}


