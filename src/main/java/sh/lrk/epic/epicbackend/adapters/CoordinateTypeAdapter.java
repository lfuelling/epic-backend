package sh.lrk.epic.epicbackend.adapters;


import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.lrk.epic.epicbackend.entities.entry.Coordinate;


import java.io.IOException;

class CoordinateTypeAdapter extends TypeAdapter<Coordinate> {
    private static final Logger log = LoggerFactory.getLogger(CoordinateTypeAdapter.class);

    @Override
    public void write(JsonWriter out, Coordinate value) {
        try {
            out.beginObject();
            out.name("lat");
            out.value(value.getLat());
            out.name("lon");
            out.value(value.getLon());
            out.endObject();
        } catch (IOException e) {
            log.error("Unable to write JSON!", e);
        }
    }

    @Override
    public Coordinate read(JsonReader in) throws IOException {
        Coordinate result = new Coordinate();
        in.beginObject();
        String currentField = null;
        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                currentField = in.nextName();
            }

            if ("lat".equals(currentField)) {
                //move to next token
                token = in.peek();
                result.setLat(in.nextDouble());
            }

            if ("lon".equals(currentField)) {
                //move to next token
                token = in.peek();
                result.setLon(in.nextDouble());
            }
        }
        in.endObject();
        return result;
    }
}

