package ru.panov.crudapp.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.panov.crudapp.model.Writer;

import java.lang.reflect.Type;

public class WriterJsonSerializer implements JsonSerializer<Writer> {

    @Override
    public JsonElement serialize(Writer writer, Type type, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.add("id", context.serialize(writer.getId()));
        object.add("firstName", context.serialize(writer.getFirstName()));
        object.add("lastName", context.serialize(writer.getLastName()));
        object.add("posts", context.serialize(writer.getPosts()));
        object.add("status", context.serialize(writer.getStatus()));
        return object;
    }
}