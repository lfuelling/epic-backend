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
import sh.lrk.epic.epicbackend.repos.*;
import sh.lrk.epic.epicbackend.services.StorageService;

@RestController
public class UploadController {

    private final Gson gson;
    private final EntryRepo entryRepo;
    private final PositionRepo positionRepo;
    private final QuaternionsRepo quaternionsRepo;
    private final CoordsRepo coordsRepo;
    private final CoordinateRepo coordinateRepo;
    private final StorageService storageService;
    private static final Logger log = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    public UploadController(EntryRepo entryRepo, StorageService storageService, DataProperties dataProperties, PositionRepo positionRepo, QuaternionsRepo quaternionsRepo, CoordsRepo coordsRepo, CoordinateRepo coordinateRepo) {
        this.entryRepo = entryRepo;
        this.storageService = storageService;
        this.gson = EpicBackendApplication.getGson(dataProperties.getDateFormat());
        this.positionRepo = positionRepo;
        this.quaternionsRepo = quaternionsRepo;
        this.coordsRepo = coordsRepo;
        this.coordinateRepo = coordinateRepo;
    }

    @PostMapping("/save")
    public RedirectView postImage(@RequestParam("image") MultipartFile file,
                                  @RequestParam("json") String entryJson,
                                  RedirectAttributes redirectAttributes) {
        try {
            Entry entry = saveEntry(entryJson);
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

    private Entry saveEntry(@RequestParam("json") String entryJson) {
        Entry entry = gson.fromJson(entryJson, Entry.class);
        entry.setAttitudeQuarternions(quaternionsRepo.save(entry.getAttitudeQuarternions()));
        entry.setCentroidCoordinates(coordinateRepo.save(entry.getCentroidCoordinates()));
        entry.setDscovrJ2000Position(positionRepo.save(entry.getDscovrJ2000Position()));
        entry.setSunJ2000Position(positionRepo.save(entry.getSunJ2000Position()));
        entry.setLunarJ2000Position(positionRepo.save(entry.getLunarJ2000Position()));
        entry.getCoords().setAttitudeQuarternions(quaternionsRepo.save(entry.getCoords().getAttitudeQuarternions()));
        entry.getCoords().setCentroidCoordinates(coordinateRepo.save(entry.getCoords().getCentroidCoordinates()));
        entry.getCoords().setDscovrJ2000Position(positionRepo.save(entry.getCoords().getDscovrJ2000Position()));
        entry.getCoords().setLunarJ2000Position(positionRepo.save(entry.getCoords().getLunarJ2000Position()));
        entry.getCoords().setSunJ2000Position(positionRepo.save(entry.getCoords().getSunJ2000Position()));
        entry.setCoords(coordsRepo.save(entry.getCoords()));
        entryRepo.save(entry);
        return entry;
    }
}
