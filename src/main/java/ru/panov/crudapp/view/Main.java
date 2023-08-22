package ru.panov.crudapp.view;

import com.google.gson.GsonBuilder;
import ru.panov.crudapp.model.Post;
import ru.panov.crudapp.model.Writer;
import ru.panov.crudapp.repository.gson.GsonWriterRepositoryImpl;
import ru.panov.crudapp.util.LocalDateTimeTypeAdapter;
import ru.panov.crudapp.util.WriterJsonSerializer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Post post = new Post("sadas", null);
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        Writer writer2 = new Writer("s", "w", posts);
        Writer writer1 = new Writer("s3", "w2", posts);
        GsonWriterRepositoryImpl writer = new GsonWriterRepositoryImpl(
                new GsonBuilder().setPrettyPrinting().serializeNulls()
                        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                        .registerTypeAdapter(Writer.class, new WriterJsonSerializer()).create());

        writer.save(writer1);
        writer.save(writer2);
        System.out.println("get all: " + writer.getAll());
        System.out.println("get by id  = 1 : " + writer.getById(1));
        writer.deleteById(1);
        System.out.println("get all after dell writer by id = 10:" + writer.getAll());
    }
}
