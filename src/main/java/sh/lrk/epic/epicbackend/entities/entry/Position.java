package sh.lrk.epic.epicbackend.entities.entry;

import java.io.Serializable;

public class Position implements Serializable {
    private final Float x;
    private final Float y;
    private final Float z;

    public Position(Float x, Float y, Float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Float getZ() {
        return z;
    }
}
