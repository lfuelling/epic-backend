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
    private Double q0;
    private Double q1;
    private Double q2;
    private Double q3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQ0() {
        return q0;
    }

    public void setQ0(Double q0) {
        this.q0 = q0;
    }

    public Double getQ1() {
        return q1;
    }

    public void setQ1(Double q1) {
        this.q1 = q1;
    }

    public Double getQ2() {
        return q2;
    }

    public void setQ2(Double q2) {
        this.q2 = q2;
    }

    public Double getQ3() {
        return q3;
    }

    public void setQ3(Double q3) {
        this.q3 = q3;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Quaternions &&
                ((id != null && id.equals(((Quaternions) obj).id)) || (((Quaternions) obj).id == null)) &&
                ((q0 != null && q0.equals(((Quaternions) obj).q0)) || (((Quaternions) obj).q0 == null)) &&
                ((q1 != null && q1.equals(((Quaternions) obj).q1)) || (((Quaternions) obj).q1 == null)) &&
                ((q2 != null && q2.equals(((Quaternions) obj).q2)) || (((Quaternions) obj).q2 == null)) &&
                ((q3 != null && q3.equals(((Quaternions) obj).q3)) || (((Quaternions) obj).q3 == null));
    }
}
