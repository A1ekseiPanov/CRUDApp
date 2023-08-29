package ru.panov.crudapp;

import com.google.gson.GsonBuilder;
import ru.panov.crudapp.controller.LabelController;
import ru.panov.crudapp.controller.PostController;
import ru.panov.crudapp.controller.WriterController;
import ru.panov.crudapp.repository.LabelRepository;
import ru.panov.crudapp.repository.PostRepository;
import ru.panov.crudapp.repository.WriterRepository;
import ru.panov.crudapp.repository.gson.GsonLabelRepositoryImpl;
import ru.panov.crudapp.repository.gson.GsonPostRepositoryImpl;
import ru.panov.crudapp.repository.gson.GsonWriterRepositoryImpl;
import ru.panov.crudapp.util.LocalDateTimeTypeAdapter;
import ru.panov.crudapp.view.LabelView;
import ru.panov.crudapp.view.PostView;
import ru.panov.crudapp.view.WriterView;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Context {
    private final WriterRepository writerRepository = new GsonWriterRepositoryImpl(
            new GsonBuilder().setPrettyPrinting().serializeNulls()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                    .create());

    private final PostRepository postRepository = new GsonPostRepositoryImpl(
            new GsonBuilder().setPrettyPrinting().serializeNulls()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                    .create(), writerRepository);

    private final LabelRepository labelRepository = new GsonLabelRepositoryImpl(new GsonBuilder().setPrettyPrinting().serializeNulls()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .create(), postRepository);

    Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("Select an option:");
            System.out.println("1. Writer");
            System.out.println("2. Post");
            System.out.println("3. Label");
            System.out.println("0. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> writerRun();
                case "2" -> postRun();
                case "3" -> labelRun();
                case "0" -> running = false;
                default -> System.out.println("Invalid choice");
            }
        }
    }

    public void writerRun() {
        WriterView view = new WriterView(new WriterController(writerRepository, postRepository));
        view.run();
    }

    public void postRun() {
        PostView post = new PostView(new PostController(postRepository, labelRepository));
        post.run();
    }

    public void labelRun() {
        LabelView labelView = new LabelView(new LabelController(labelRepository));
        labelView.run();
    }
}
