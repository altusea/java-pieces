package moe.nova.util.internal.gson;

import cn.hutool.v7.core.date.DateFormatPool;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.YearMonth;

public final class YearMonthTypeAdapter extends TypeAdapter<YearMonth> {

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {

        @Override
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == YearMonth.class ? (TypeAdapter<T>) new YearMonthTypeAdapter() : null;
        }
    };

    @Override
    public void write(final JsonWriter jsonWriter, final YearMonth yearMonth) throws IOException {
        if (yearMonth == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(yearMonth.format(DateFormatPool.NORM_MONTH_FORMATTER));
        }
    }

    @Override
    public YearMonth read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        } else {
            return YearMonth.parse(jsonReader.nextString(), DateFormatPool.NORM_MONTH_FORMATTER);
        }
    }
}
