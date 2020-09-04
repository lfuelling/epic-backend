package sh.lrk.epic.epicbackend.repos;

import org.springframework.data.repository.CrudRepository;
import sh.lrk.epic.epicbackend.entities.entry.Coords;

public interface CoordsRepo extends CrudRepository<Coords, Long> {
}
