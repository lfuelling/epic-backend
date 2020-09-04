package sh.lrk.epic.epicbackend.entities.entry;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Entry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String image;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Coords coords;
    private String caption;
    private String version;
    private String identifier;
    private LocalDateTime date;

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

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public Coords getCoords() {
        return coords;
    }

    public String getCaption() {
        return caption;
    }

    public String getVersion() {
        return version;
    }

    public String getIdentifier() {
        return identifier;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Coordinate getCentroidCoordinates() {
        return centroidCoordinates;
    }

    public Position getDscovrJ2000Position() {
        return dscovrJ2000Position;
    }

    public Position getLunarJ2000Position() {
        return lunarJ2000Position;
    }

    public Position getSunJ2000Position() {
        return sunJ2000Position;
    }

    public Quaternions getAttitudeQuarternions() {
        return attitudeQuarternions;
    }
}
