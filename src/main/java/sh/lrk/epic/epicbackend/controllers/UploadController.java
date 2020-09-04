package sh.lrk.epic.epicbackend.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import sh.lrk.epic.epicbackend.EpicBackendApplication;
import sh.lrk.epic.epicbackend.StorageException;
import sh.lrk.epic.epicbackend.entities.entry.Entry;
import sh.lrk.epic.epicbackend.properties.DataProperties;
import sh.lrk.epic.epicbackend.repos.EntryRepo;
import sh.lrk.epic.epicbackend.services.StorageService;

@RestController
public class UploadController {

    private final Gson gson;
    private final EntryRepo entryRepo;
    private final StorageService storageService;
    private static final Logger log = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    public UploadController(EntryRepo entryRepo, StorageService storageService, DataProperties dataProperties) {
        this.entryRepo = entryRepo;
        this.storageService = storageService;
        this.gson = EpicBackendApplication.getGson(dataProperties.getDateFormat());
    }

    @PostMapping("/save")
    public RedirectView postImage(@RequestParam("image") MultipartFile file,
                                  @RequestParam("json") String entryJson,
                                  RedirectAttributes redirectAttributes) {
        try {
            Entry entry = gson.fromJson(entryJson, Entry.class);
            entryRepo.save(entry);
            storageService.store(file);
            final String successText = "Successfully uploaded entry '" + entry.getIdentifier() + "' (Image: '" + file.getOriginalFilename() + "')!";
            redirectAttributes.addFlashAttribute("message", successText);
            return new RedirectView("upload");
        } catch (StorageException e) {
            log.error("Unable to store uploaded image!", e);
            redirectAttributes.addFlashAttribute("message", "Error! Unable to save data!");
            return new RedirectView("upload");
        }
    }
}
