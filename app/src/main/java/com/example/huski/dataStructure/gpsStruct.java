package com.example.huski.dataStructure;

/**
 * The structure that's describing the gps infos of the components
 */
public class gpsStruct {
    private double lon;
    private double lat;
    private double alt;

    public gpsStruct(double lon, double lat, double alt){
        this.lon = lon;
        this.lat = lat;
        this.alt = alt;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    /**
     * Calculates the angle between the ski, the user and the lat axis
     * @param target the ski to localise
     * @return the angle describe above
     */
    public float getAngle(gpsStruct target){
        double lat2 = target.getLat();
        double lon2 = target.getLon();
        double dy = lat2 -this.lat;
        double dx = lon2 - this.lon;
        if(dy == 0 && dx == 0){ // if the lat and long are the same as the user's position we chose to point towards north
            return 0;
        }
        if(dy == 0 && lon2 > this.lon){
            return 90; //east
        }
        else if(dy == 0 && lon2 <= this.lon){
            return -90; //west
        }
        if(dx == 0 && lat2 > this.lat){
            return 0; //north
        }
        else if(dx == 0 && lat2 <= this.lat){
            return 180; //south
        }
        double y = Math.sin(Math.toRadians(dy)) * Math.cos(Math.toRadians(lon2));
        double x = Math.cos(Math.toRadians(this.lon)) * Math.sin(Math.toRadians(lon2)) -
                Math.sin(Math.toRadians(this.lon)) * Math.cos(Math.toRadians(lon2))*Math.cos(Math.toRadians(dy));
        double angle = (Math.toDegrees(Math.atan2(x, y)) + 360) % 360;
        return (float) -angle;
    }

    /**
     * Calculates the distance between 2 gps points
     * @param p2 the point to calculate the distance to
     * @return the distance in meters
     */
    public double distance(gpsStruct p2) {
        double lat2 = p2.getLat();
        double lon2 = p2.getLon();
        double el2 = p2.getAlt();
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - this.lat);
        double lonDistance = Math.toRadians(lon2 - this.lon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.lat)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = this.alt - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }


}
