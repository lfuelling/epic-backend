package sh.lrk.epic.epicbackend.repos;

import org.springframework.data.repository.CrudRepository;
import sh.lrk.epic.epicbackend.entities.entry.Coordinate;

public interface CoordinateRepo extends CrudRepository<Coordinate, Long> {

}
