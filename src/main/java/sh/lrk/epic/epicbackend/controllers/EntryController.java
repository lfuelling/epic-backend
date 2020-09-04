package sh.lrk.epic.epicbackend.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sh.lrk.epic.epicbackend.entities.entry.Entry;
import sh.lrk.epic.epicbackend.repos.EntryRepo;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
public class EntryController {

    private final EntryRepo entryRepo;
    private final Gson gson;

    @Autowired
    public EntryController(EntryRepo entryRepo) {
        this.entryRepo = entryRepo;
        gson = new Gson();
    }

    @GetMapping("/entries")
    public List<String> getAllIdentifiers() {
        final List<String> result = new ArrayList<>();
        entryRepo.findAll().forEach(e -> result.add(e.getIdentifier()));
        return result;
    }

    @GetMapping("/entry/{identifier}")
    public Entry getEntry(@NotEmpty @NotNull @PathVariable String identifier) {
        if (!identifier.isEmpty() && !identifier.isBlank()) {
            return entryRepo.findByIdentifier(identifier);
        }
        return null;
    }

}
