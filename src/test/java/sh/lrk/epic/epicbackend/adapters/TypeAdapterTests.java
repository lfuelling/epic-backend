package sh.lrk.epic.epicbackend.adapters;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import sh.lrk.epic.epicbackend.EpicBackendApplication;
import sh.lrk.epic.epicbackend.entities.entry.Entry;
import sh.lrk.epic.epicbackend.properties.DataProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@SpringBootTest
public class TypeAdapterTests {

    private final DataProperties dataProperties;

    @Autowired
    public TypeAdapterTests(DataProperties dataProperties) {
        this.dataProperties = dataProperties;
    }

    @Test
    void readAndRestoreSampleEntry() throws IOException {
        Gson gson = EpicBackendApplication.getGson(dataProperties.getDateFormat());
        String originalJson = readResource("sample.json");
        Entry parsedEntry = gson.fromJson(originalJson, Entry.class);
        validateEntryProperties(parsedEntry);
        String generatedJson = gson.toJson(parsedEntry);
        Entry generatedEntry = gson.fromJson(generatedJson, Entry.class);
        validateEntryProperties(generatedEntry);
        Assert.isTrue(generatedEntry.equals(parsedEntry), "Entries are not the same!");
    }

    private static void validateEntryProperties(Entry parsedEntry) {
        Assert.notNull(parsedEntry.getCoords(), "Coords are null!");
        Assert.notNull(parsedEntry.getAttitudeQuarternions(), "AttitudeQuarternions are null!");
        Assert.notNull(parsedEntry.getCentroidCoordinates(), "CentroidCoordinates are null!");
        Assert.notNull(parsedEntry.getDscovrJ2000Position(), "DscovrJ2000Position is null!");
        Assert.notNull(parsedEntry.getSunJ2000Position(), "SunJ2000Position is null!");
        Assert.notNull(parsedEntry.getLunarJ2000Position(), "LunarJ2000Position is null!");
        //TODO maybe add the rest, but that's not really important...
    }

    private static String readResource(String filename) throws IOException {
        InputStream resourceAsStream = TypeAdapterTests.class.getClassLoader().getResourceAsStream(filename);
        Assert.notNull(resourceAsStream, "Unable to open InputStream!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();
        return stringBuilder.toString();
    }
}
