package sh.lrk.epic.epicbackend.entities.entry;

import java.io.Serializable;

public class Quaternions implements Serializable {
    private final Float q0;
    private final Float q1;
    private final Float q2;
    private final Float q3;

    public Quaternions(Float q0, Float q1, Float q2, Float q3) {
        this.q0 = q0;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }

    public Float getQ0() {
        return q0;
    }

    public Float getQ1() {
        return q1;
    }

    public Float getQ2() {
        return q2;
    }

    public Float getQ3() {
        return q3;
    }
}
