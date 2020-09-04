package sh.lrk.epic.epicbackend.entities.entry;

import java.io.Serializable;

public class Coordinate implements Serializable {
    private final Float lat;
    private final Float lon;

    public Coordinate(Float lat, Float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLon() {
        return lon;
    }
}
