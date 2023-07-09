package ru.stqa.pft.addressbook.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.File;
import java.lang.reflect.Type;

public class FileSerializer implements JsonSerializer<File> {
    @Override
    public JsonElement serialize(File src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src.getPath());
    }
}
