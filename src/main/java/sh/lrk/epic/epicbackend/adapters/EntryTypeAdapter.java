package sh.lrk.epic.epicbackend.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.lrk.epic.epicbackend.entities.entry.Entry;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class EntryTypeAdapter extends TypeAdapter<Entry> {
    private final DateTimeFormatter dateTimeFormatter;
    private final QuaternionsTypeAdapter quaternionsTypeAdapter;
    private final CoordinateTypeAdapter coordinateTypeAdapter;
    private final PositionTypeAdapter positionTypeAdapter;
    private final CoordsTypeAdapter coordsTypeAdapter;

    EntryTypeAdapter(String dateFormat) {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        quaternionsTypeAdapter = new QuaternionsTypeAdapter();
        coordinateTypeAdapter = new CoordinateTypeAdapter();
        positionTypeAdapter = new PositionTypeAdapter();
        coordsTypeAdapter = new CoordsTypeAdapter();
    }

    private static final Logger log = LoggerFactory.getLogger(EntryTypeAdapter.class);

    @Override
    public void write(JsonWriter out, Entry value) {
        try {
            out.beginObject();
            out.name("identifier");
            out.value(value.getIdentifier());
            out.name("image");
            out.value(value.getImage());
            out.name("caption");
            out.value(value.getCaption());
            out.name("version");
            out.value(value.getVersion());
            out.name("date");
            out.value(dateTimeFormatter.format(value.getDate()));
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
            out.name("coords");
            out.jsonValue(coordsTypeAdapter.toJson(value.getCoords()));
            out.endObject();
        } catch (IOException e) {
            log.error("Unable to write JSON!", e);
        }
    }

    @Override
    public Entry read(JsonReader in) throws IOException {
        Entry result = new Entry();
        in.beginObject();
        String fieldname = null;

        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldname = in.nextName();
            }

            if ("identifier".equals(fieldname)) {
                //move to next token
                token = in.peek();
                result.setIdentifier(in.nextString());
            }

            if ("caption".equals(fieldname)) {
                //move to next token
                token = in.peek();
                result.setCaption(in.nextString());
            }

            if ("version".equals(fieldname)) {
                //move to next token
                token = in.peek();
                result.setVersion(in.nextString());
            }

            if ("image".equals(fieldname)) {
                //move to next token
                token = in.peek();
                result.setImage(in.nextString());
            }

            if ("date".equals(fieldname)) {
                //move to next token
                token = in.peek();
                result.setDate(LocalDateTime.parse(in.nextString(), dateTimeFormatter));
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

            if ("coords".equals(fieldname)) {
                //move to next token
                token = in.peek();
                result.setCoords(coordsTypeAdapter.read(in));
            }
        }
        in.endObject();
        return result;
    }
}
