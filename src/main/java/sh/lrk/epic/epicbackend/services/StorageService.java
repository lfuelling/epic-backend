package sh.lrk.epic.epicbackend.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import sh.lrk.epic.epicbackend.FileNotFoundException;
import sh.lrk.epic.epicbackend.StorageException;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init() throws StorageException;

    String store(MultipartFile file) throws StorageException;

    Stream<Path> loadAll() throws StorageException;

    Path load(String filename);

    Resource loadAsResource(String filename) throws FileNotFoundException;

    void deleteAll();

}