package sh.lrk.epic.epicbackend.repos;

import org.springframework.data.repository.CrudRepository;
import sh.lrk.epic.epicbackend.entities.entry.Entry;

import java.time.LocalDateTime;
import java.util.List;

public interface EntryRepo extends CrudRepository<Entry, Long> {
    Entry findByIdentifier(String identifier);
    List<Entry> findByDate(LocalDateTime date);
}