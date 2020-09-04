package sh.lrk.epic.epicbackend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sh.lrk.epic.epicbackend.StorageException;
import sh.lrk.epic.epicbackend.entities.entry.Entry;
import sh.lrk.epic.epicbackend.repos.EntryRepo;
import sh.lrk.epic.epicbackend.services.StorageService;

@RestController
public class UploadController {

    private final EntryRepo entryRepo;
    private final StorageService storageService;
    private static final Logger log = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    public UploadController(EntryRepo entryRepo, StorageService storageService) {
        this.entryRepo = entryRepo;
        this.storageService = storageService;
    }

    @PostMapping("/save")
    public String postImage(@RequestParam("image") MultipartFile file,
                                    @RequestParam("json") Entry entry,
                                    RedirectAttributes redirectAttributes) {
        try {
            entryRepo.save(entry);
            storageService.store(file);
            final String successText = "Successfully uploaded entry '" + entry.getIdentifier() + "' (Image: '" + file.getOriginalFilename() + "')!";
            redirectAttributes.addFlashAttribute("message", successText);
            return "redirect:/";
        } catch (StorageException e) {
            log.error("Unable to store uploaded image!", e);
            redirectAttributes.addFlashAttribute("message", "Unable to save data!");
            return "redirect:/";
        }
    }
}
