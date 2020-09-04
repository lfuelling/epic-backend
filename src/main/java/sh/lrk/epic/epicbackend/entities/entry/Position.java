package sh.lrk.epic.epicbackend.entities.entry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double x;
    private Double y;
    private Double z;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Position &&
                ((id != null && id.equals(((Position) obj).id)) || (((Position) obj).id == null)) &&
                ((x != null && x.equals(((Position) obj).x)) || (((Position) obj).x == null)) &&
                ((y != null && y.equals(((Position) obj).y)) || (((Position) obj).y == null)) &&
                ((z != null && z.equals(((Position) obj).z)) || (((Position) obj).z == null));
    }
}
