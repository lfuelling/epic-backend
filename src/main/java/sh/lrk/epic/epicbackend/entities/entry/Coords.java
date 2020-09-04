package sh.lrk.epic.epicbackend.entities.entry;

import java.io.Serializable;

public class Coords implements Serializable {
    private final Coordinate centroidCoordinates;
    private final Position dscovrJ2000Position;
    private final Position lunarJ2000Position;
    private final Position sunJ2000Position;
    private final Quaternions attitudeQuarternions;

    public Coords(Coordinate centroidCoordinates, Position dscovrJ2000Position, Position lunarJ2000Position, Position sunJ2000Position, Quaternions attitudeQuarternions) {
        this.centroidCoordinates = centroidCoordinates;
        this.dscovrJ2000Position = dscovrJ2000Position;
        this.lunarJ2000Position = lunarJ2000Position;
        this.sunJ2000Position = sunJ2000Position;
        this.attitudeQuarternions = attitudeQuarternions;
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
