package ru.stqa.pft.addressbook.serializers;

import com.google.gson.*;

import java.io.File;
import java.lang.reflect.Type;

public class FileDeserializer implements JsonDeserializer<File> {
    @Override
    public File deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new File(json.getAsString());
    }
}
