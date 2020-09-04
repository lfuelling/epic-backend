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

    private String caption;
    private String version;
    private String identifier;
    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Entry &&
                ((caption != null && caption.equals(((Entry) obj).caption)) || (((Entry) obj).caption == null)) &&
                ((date != null && date.equals(((Entry) obj).date)) || (((Entry) obj).date == null)) &&
                ((identifier != null && identifier.equals(((Entry) obj).identifier)) || (((Entry) obj).identifier == null)) &&
                ((version != null && version.equals(((Entry) obj).version)) || (((Entry) obj).version == null)) &&
                ((attitudeQuarternions != null && attitudeQuarternions.equals(((Entry) obj).attitudeQuarternions)) || (((Entry) obj).attitudeQuarternions == null)) &&
                ((dscovrJ2000Position != null && dscovrJ2000Position.equals(((Entry) obj).dscovrJ2000Position)) || (((Entry) obj).dscovrJ2000Position == null)) &&
                ((lunarJ2000Position != null && lunarJ2000Position.equals(((Entry) obj).lunarJ2000Position)) || (((Entry) obj).lunarJ2000Position == null)) &&
                ((sunJ2000Position != null && sunJ2000Position.equals(((Entry) obj).sunJ2000Position)) || (((Entry) obj).sunJ2000Position == null)) &&
                ((centroidCoordinates != null && centroidCoordinates.equals(((Entry) obj).centroidCoordinates)) || (((Entry) obj).centroidCoordinates == null)) &&
                ((coords != null && coords.equals(((Entry) obj).coords)) || (((Entry) obj).coords == null)) &&
                ((id != null && id.equals(((Entry) obj).id)) || (((Entry) obj).id == null));
    }
}
