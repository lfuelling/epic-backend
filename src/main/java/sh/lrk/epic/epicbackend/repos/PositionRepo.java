package sh.lrk.epic.epicbackend.repos;

import org.springframework.data.repository.CrudRepository;
import sh.lrk.epic.epicbackend.entities.entry.Position;

public interface PositionRepo extends CrudRepository<Position, Long> {
}
