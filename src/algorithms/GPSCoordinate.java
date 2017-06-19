package algorithms;

import static java.lang.Math.toRadians;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.atan2;

public class GPSCoordinate {
	public final double latitude;
	public final double longitude;
	public GPSCoordinate(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Computes the haversine distance, in meters, of this location
	 * to another location. As for any haversine distance, it is not
	 * precise for very small distances.
	 * @param other The location to compute the distance to.
	 * @return The haversine distance, in meters.
	 */
	public double distance(GPSCoordinate other) {
		//double R = 6378137; // Earth Equatorial radius in meters.
		double R = 6371000.8; // Earth volumetric radius in meters (IUGG).
	
		double dlat = toRadians(latitude - other.latitude);
		double dlon = toRadians(longitude - other.longitude);
		double coslat = cos(toRadians(latitude));
		double cosother = cos(toRadians(other.latitude));
		
		// compute haversine
		double a = pow(sin(dlat)/2.0,2) + coslat*cosother*pow(sin(dlon/2.0),2);  
		double c = 2 * atan2(sqrt(a), sqrt(1-a));
		double d = R * c;
		
		return d;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof GPSCoordinate) {
			GPSCoordinate other = (GPSCoordinate)o;
			return latitude==other.latitude && longitude==other.longitude;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return String.format("%.8g%.8g", latitude, longitude).hashCode();
	}

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
        
        
}
