package sh.lrk.epic.epicbackend.entities.entry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Coordinate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double lat;
    private Double lon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coordinate &&
                ((id != null && id.equals(((Coordinate) obj).id)) || (((Coordinate) obj).id == null)) &&
                ((lat != null && lat.equals(((Coordinate) obj).lat)) || (((Coordinate) obj).lat == null)) &&
                ((id != null && id.equals(((Coordinate) obj).id)) || (((Coordinate) obj).id == null));
    }
}
