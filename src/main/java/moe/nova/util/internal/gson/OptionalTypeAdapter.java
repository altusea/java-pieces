package moe.nova.util.internal.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public class OptionalTypeAdapter<T> extends TypeAdapter<Optional<T>> {

    private static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @Override
        @SuppressWarnings("unchecked")
        public <U> TypeAdapter<U> create(Gson gson, TypeToken<U> type) {
            if (Optional.class.isAssignableFrom(type.getRawType())) {
                return new OptionalTypeAdapter(gson, type);
            }
            return null;
        }
    };

    public static TypeAdapterFactory factory() {
        return FACTORY;
    }

    private final TypeAdapter<T> valueAdapter;

    public OptionalTypeAdapter(Gson gson, TypeToken<Optional<T>> type) {
        if (!Optional.class.isAssignableFrom(type.getRawType()))
            throw new IllegalArgumentException();

        Type javaType = type.getType();

        if (javaType instanceof ParameterizedType) {
            this.valueAdapter = (TypeAdapter<T>) gson.getAdapter(TypeToken.get(((ParameterizedType) javaType).getActualTypeArguments()[0]));
        } else {
            this.valueAdapter = (TypeAdapter<T>) gson.getAdapter(Object.class);
        }
    }

    @Override
    public void write(JsonWriter out, Optional<T> value) throws IOException {
        out.beginArray();
        if (value.isPresent()) {
            valueAdapter.write(out, value.get());
        }
        out.endArray();
    }

    @Override
    public Optional<T> read(JsonReader in) throws IOException {
        Optional<T> res;
        in.beginArray();
        if (in.peek() == JsonToken.END_ARRAY)
            res = Optional.empty();
        else
            res = Optional.of(valueAdapter.read(in));
        in.endArray();

        return res;
    }
}
