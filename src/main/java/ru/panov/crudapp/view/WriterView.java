package ru.panov.crudapp.view;

import ru.panov.crudapp.controller.WriterController;
import ru.panov.crudapp.model.Label;
import ru.panov.crudapp.model.Post;
import ru.panov.crudapp.model.Writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterView {
    private final WriterController writerController;
    private final Scanner scanner;

    public WriterView(WriterController writerController) {
        this.writerController = writerController;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("1. Create Writer");
            System.out.println("2. Get Writer by ID");
            System.out.println("3. Get All Writers");
            System.out.println("4. Update Writer");
            System.out.println("5. Delete Writer");
            System.out.println("6. Add post to Writer");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String option = scanner.nextLine();

            System.out.println();

            switch (option) {
                case "1" -> create();
                case "2" -> get();
                case "3" -> getAll();
                case "4" -> update();
                case "5" -> delete();
                case "6" -> addPost();
                case "0" -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void create() {
        System.out.print("Enter firstname: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter lastname: ");
        String lastname = scanner.nextLine();
        List<Post> posts = new ArrayList<>();
        Writer newWriter = writerController.create(firstname, lastname, posts);
        System.out.println();
        System.out.println(newWriter);
        System.out.println();
    }

    public void get() {
        System.out.print("Enter Writer id: ");
        String id = scanner.nextLine();
        Writer writer = writerController.get(Integer.parseInt(id));
        System.out.println();
        System.out.println("Writer found: " + writer);
        System.out.println();
    }

    public void getAll() {
        List<Writer> writers = writerController.getAll();
        for (Writer writer : writers) {
            System.out.println(writer);
            System.out.println();
        }
    }

    public void update() {
        System.out.print("Enter Writer id to update: ");
        String id = scanner.nextLine();
        System.out.println();
        System.out.print("Enter update firstname: ");
        String firstname = scanner.nextLine();
        System.out.println();
        System.out.print("Enter update lastname: ");
        String lastname = scanner.nextLine();
        System.out.println();
        Writer updateWriter = writerController.update(Integer.parseInt(id), firstname, lastname);
        System.out.println();
        System.out.println(updateWriter);
        System.out.println();
    }

    public void delete() {
        System.out.print("Enter writer ID to delete: ");
        String id = scanner.nextLine();
        writerController.delete(Integer.parseInt(id));
        System.out.println();
        System.out.println("Writer by id: " + id + " deleted.");
        System.out.println();
    }

    public void addPost() {
        System.out.print("Enter Writer ID to add a post to: ");
        String writerId = scanner.nextLine();
        System.out.println();
        System.out.print("Enter post content: ");
        String content = scanner.nextLine();
        List<Label> labels = new ArrayList<>();
        Post newPost = new Post(content, labels);
        Writer updatedWriter = writerController.addPost(Integer.parseInt(writerId), newPost);
        System.out.println();
        System.out.println("Post added to Writer with ID: " + updatedWriter);
        System.out.println();
    }
}
