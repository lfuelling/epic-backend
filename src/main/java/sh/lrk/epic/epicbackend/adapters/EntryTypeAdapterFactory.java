package sh.lrk.epic.epicbackend.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import sh.lrk.epic.epicbackend.entities.entry.Entry;

public class EntryTypeAdapterFactory implements TypeAdapterFactory {

    private final String dateFormat;

    public EntryTypeAdapterFactory(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (!Entry.class.isAssignableFrom(type.getRawType())) return null;
        //noinspection unchecked
        return (TypeAdapter<T>) new EntryTypeAdapter(dateFormat);
    }
}
