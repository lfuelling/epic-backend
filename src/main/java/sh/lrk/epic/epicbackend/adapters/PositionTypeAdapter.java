package sh.lrk.epic.epicbackend.adapters;


import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.lrk.epic.epicbackend.entities.entry.Position;

import java.io.IOException;

class PositionTypeAdapter extends TypeAdapter<Position> {
    private static final Logger log = LoggerFactory.getLogger(PositionTypeAdapter.class);

    @Override
    public void write(JsonWriter out, Position value) {
        try {
            out.beginObject();
            out.name("x");
            out.value(value.getX());
            out.name("y");
            out.value(value.getY());
            out.name("z");
            out.value(value.getZ());
            out.endObject();
        } catch (IOException e) {
            log.error("Unable to write JSON!", e);
        }
    }

    @Override
    public Position read(JsonReader in) throws IOException {
        Position result = new Position();
        in.beginObject();
        String currentField = null;
        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                currentField = in.nextName();
            }

            if ("x".equals(currentField)) {
                //move to next token
                token = in.peek();
                result.setX(in.nextDouble());
            }

            if ("y".equals(currentField)) {
                //move to next token
                token = in.peek();
                result.setY(in.nextDouble());
            }

            if ("z".equals(currentField)) {
                //move to next token
                token = in.peek();
                result.setZ(in.nextDouble());
            }
        }
        in.endObject();
        return result;
    }
}

