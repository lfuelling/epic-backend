package sh.lrk.epic.epicbackend.repos;

import org.springframework.data.repository.CrudRepository;
import sh.lrk.epic.epicbackend.entities.entry.Quaternions;

public interface QuaternionsRepo extends CrudRepository<Quaternions, Long> {
}
