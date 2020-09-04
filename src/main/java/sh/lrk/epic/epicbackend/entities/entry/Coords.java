package sh.lrk.epic.epicbackend.entities.entry;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Coords implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long coordsId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Coordinate centroidCoordinates;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Position dscovrJ2000Position;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Position lunarJ2000Position;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Position sunJ2000Position;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Quaternions attitudeQuarternions;

    public Long getCoordsId() {
        return coordsId;
    }

    public void setCoordsId(Long id) {
        this.coordsId = id;
    }

    public Coordinate getCentroidCoordinates() {
        return centroidCoordinates;
    }

    public void setCentroidCoordinates(Coordinate centroidCoordinates) {
        this.centroidCoordinates = centroidCoordinates;
    }

    public Position getDscovrJ2000Position() {
        return dscovrJ2000Position;
    }

    public void setDscovrJ2000Position(Position dscovrJ2000Position) {
        this.dscovrJ2000Position = dscovrJ2000Position;
    }

    public Position getLunarJ2000Position() {
        return lunarJ2000Position;
    }

    public void setLunarJ2000Position(Position lunarJ2000Position) {
        this.lunarJ2000Position = lunarJ2000Position;
    }

    public Position getSunJ2000Position() {
        return sunJ2000Position;
    }

    public void setSunJ2000Position(Position sunJ2000Position) {
        this.sunJ2000Position = sunJ2000Position;
    }

    public Quaternions getAttitudeQuarternions() {
        return attitudeQuarternions;
    }

    public void setAttitudeQuarternions(Quaternions attitudeQuarternions) {
        this.attitudeQuarternions = attitudeQuarternions;
    }
}
