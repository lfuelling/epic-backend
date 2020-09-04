package sh.lrk.epic.epicbackend.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.lrk.epic.epicbackend.entities.entry.Quaternions;

import java.io.IOException;

class QuaternionsTypeAdapter extends TypeAdapter<Quaternions> {
    private static final Logger log = LoggerFactory.getLogger(QuaternionsTypeAdapter.class);

    @Override
    public void write(JsonWriter out, Quaternions value) {
        try {
            out.beginObject();
            out.name("q0");
            out.value(value.getQ0());
            out.name("q1");
            out.value(value.getQ1());
            out.name("q2");
            out.value(value.getQ2());
            out.name("q3");
            out.value(value.getQ3());
            out.endObject();
        } catch (IOException e) {
            log.error("Unable to write JSON!", e);
        }
    }

    @Override
    public Quaternions read(JsonReader in) throws IOException {
        Quaternions result = new Quaternions();
        in.beginObject();
        String currentField = null;
        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                currentField = in.nextName();
            }

            if ("q0".equals(currentField)) {
                //move to next token
                token = in.peek();
                result.setQ0(in.nextDouble());
            }

            if ("q1".equals(currentField)) {
                //move to next token
                token = in.peek();
                result.setQ1(in.nextDouble());
            }

            if ("q2".equals(currentField)) {
                //move to next token
                token = in.peek();
                result.setQ2(in.nextDouble());
            }

            if ("q3".equals(currentField)) {
                //move to next token
                token = in.peek();
                result.setQ3(in.nextDouble());
            }
        }
        in.endObject();
        return result;
    }
}

