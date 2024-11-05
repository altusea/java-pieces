package moe.nova.util.internal.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.dromara.hutool.core.date.DateFormatPool;
import org.dromara.hutool.core.date.DateUtil;

import java.io.IOException;
import java.util.Date;

public final class DateTypeAdapter extends TypeAdapter<Date> {

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {

        @Override
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == Date.class ? (TypeAdapter<T>) new DateTypeAdapter() : null;
        }
    };

    @Override
    public void write(JsonWriter jsonWriter, Date date) throws IOException {
        if (date == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(DateUtil.format(date, DateFormatPool.NORM_DATETIME_FORMAT));
        }
    }

    @Override
    public Date read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        } else {
            return DateUtil.parse(jsonReader.nextString(), DateFormatPool.NORM_DATETIME_FORMAT);
        }
    }
}
