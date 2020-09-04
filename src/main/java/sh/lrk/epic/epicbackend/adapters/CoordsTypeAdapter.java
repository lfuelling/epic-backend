package sh.lrk.epic.epicbackend.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.lrk.epic.epicbackend.entities.entry.Coords;

import java.io.IOException;

class CoordsTypeAdapter extends TypeAdapter<Coords> {
    private static final Logger log = LoggerFactory.getLogger(CoordsTypeAdapter.class);

    private final QuaternionsTypeAdapter quaternionsTypeAdapter;
    private final CoordinateTypeAdapter coordinateTypeAdapter;
    private final PositionTypeAdapter positionTypeAdapter;

    CoordsTypeAdapter() {
        quaternionsTypeAdapter = new QuaternionsTypeAdapter();
        coordinateTypeAdapter = new CoordinateTypeAdapter();
        positionTypeAdapter = new PositionTypeAdapter();
    }

    @Override
    public void write(JsonWriter out, Coords value) {
        try {
            out.beginObject();
            out.name("attitude_quaternions");
            out.jsonValue(quaternionsTypeAdapter.toJson(value.getAttitudeQuarternions()));
            out.name("sun_j2000_position");
            out.jsonValue(positionTypeAdapter.toJson(value.getSunJ2000Position()));
            out.name("lunar_j2000_position");
            out.jsonValue(positionTypeAdapter.toJson(value.getLunarJ2000Position()));
            out.name("dscovr_j2000_position");
            out.jsonValue(positionTypeAdapter.toJson(value.getDscovrJ2000Position()));
            out.name("centroid_coordinates");
            out.jsonValue(coordinateTypeAdapter.toJson(value.getCentroidCoordinates()));
            out.endObject();
        } catch (IOException e) {
            log.error("Unable to write JSON!", e);
        }
    }

    @Override
    public Coords read(JsonReader in) throws IOException {
        Coords result = new Coords();
        in.beginObject();
        String fieldname = null;

        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldname = in.nextName();
            }

            if ("attitude_quaternions".equals(fieldname)) {
                //move to next token
                token = in.peek();
                result.setAttitudeQuarternions(quaternionsTypeAdapter.read(in));
            }

            if ("sun_j2000_position".equals(fieldname)) {
                //move to next token
                token = in.peek();
                result.setSunJ2000Position(positionTypeAdapter.read(in));
            }

            if ("lunar_j2000_position".equals(fieldname)) {
                //move to next token
                token = in.peek();
                result.setLunarJ2000Position(positionTypeAdapter.read(in));
            }

            if ("dscovr_j2000_position".equals(fieldname)) {
                //move to next token
                token = in.peek();
                result.setDscovrJ2000Position(positionTypeAdapter.read(in));
            }

            if ("centroid_coordinates".equals(fieldname)) {
                //move to next token
                token = in.peek();
                result.setCentroidCoordinates(coordinateTypeAdapter.read(in));
            }
        }
        in.endObject();
        return result;
    }
}
