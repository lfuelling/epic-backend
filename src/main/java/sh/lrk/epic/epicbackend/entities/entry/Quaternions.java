package sh.lrk.epic.epicbackend.entities.entry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Quaternions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float q0;
    private Float q1;
    private Float q2;
    private Float q3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getQ0() {
        return q0;
    }

    public void setQ0(Float q0) {
        this.q0 = q0;
    }

    public Float getQ1() {
        return q1;
    }

    public void setQ1(Float q1) {
        this.q1 = q1;
    }

    public Float getQ2() {
        return q2;
    }

    public void setQ2(Float q2) {
        this.q2 = q2;
    }

    public Float getQ3() {
        return q3;
    }

    public void setQ3(Float q3) {
        this.q3 = q3;
    }
}
