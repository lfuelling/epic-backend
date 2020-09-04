package sh.lrk.epic.epicbackend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import sh.lrk.epic.epicbackend.repos.EntryRepo;
import sh.lrk.epic.epicbackend.services.StorageService;

import java.io.FileNotFoundException;

@Controller
public class ImageController {
    private final StorageService storageService;
    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    public ImageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/image/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {

        try {
            Resource file = storageService.loadAsResource(filename);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (sh.lrk.epic.epicbackend.FileNotFoundException e) {
            log.error("Unable to find image!", e);
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(FileNotFoundException ex) {
        //TODO add logging
        return ResponseEntity.notFound().build();
    }

}