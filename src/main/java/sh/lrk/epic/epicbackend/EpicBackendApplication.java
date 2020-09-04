package sh.lrk.epic.epicbackend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sh.lrk.epic.epicbackend.entities.entry.Coordinate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class EpicBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpicBackendApplication.class, args);
    }


    public static Gson getGson(String dateFormat) {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
                                LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern(dateFormat)))
                .create();
    }

}
