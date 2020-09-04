package sh.lrk.epic.epicbackend.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sh.lrk.epic.epicbackend.properties.DataProperties;
import sh.lrk.epic.epicbackend.properties.StorageProperties;

import java.io.File;
import java.time.format.DateTimeFormatter;

@Component
public class ConfigValidationRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ConfigValidationRunner.class);

    private final DataProperties dataProperties;
    private final StorageProperties storageProperties;

    public ConfigValidationRunner(DataProperties dataProperties, StorageProperties storageProperties) {
        this.dataProperties = dataProperties;
        this.storageProperties = storageProperties;
    }

    @Override
    public void run(String... args) throws Exception {
        if (storageProperties.getLocation().isEmpty() ||
                storageProperties.getLocation().isBlank() ||
                !new File(storageProperties.getLocation()).exists() ||
                !new File(storageProperties.getLocation()).isDirectory()) {
            log.error("Invalid storage configuration!");
            System.exit(1);
        }
        try {
            DateTimeFormatter.ofPattern(dataProperties.getDateFormat());
        } catch (IllegalArgumentException e) {
            log.error("Invalid date format configuration!", e);
            System.exit(1);
        }
        log.info("Configuration is valid!");
    }
}

