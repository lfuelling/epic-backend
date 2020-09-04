package sh.lrk.epic.epicbackend.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sh.lrk.epic.epicbackend.EpicBackendApplication;
import sh.lrk.epic.epicbackend.entities.entry.Entry;
import sh.lrk.epic.epicbackend.properties.DataProperties;
import sh.lrk.epic.epicbackend.repos.EntryRepo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
@RestController
public class EntryController {

    private final EntryRepo entryRepo;
    private final Gson gson;

    @Autowired
    public EntryController(EntryRepo entryRepo, DataProperties dataProperties) {
        this.entryRepo = entryRepo;
        this.gson = EpicBackendApplication.getGson(dataProperties.getDateFormat());
    }

    @GetMapping("/entries")
    public ModelAndView getAllEntries() {
        ModelAndView mav = new ModelAndView("entries");
        mav.addObject("entries", entryRepo.findAll());
        return mav;
    }

    @GetMapping("/api/entries")
    public Iterable<Entry> getAllIdentifiers() {
        return entryRepo.findAll();
    }

    @GetMapping("/api/entry/{identifier}")
    public Entry getEntry(@NotEmpty @NotNull @PathVariable String identifier) {
        if (!identifier.isEmpty() && !identifier.isBlank()) {
            return entryRepo.findByIdentifier(identifier);
        }
        return null;
    }

}
