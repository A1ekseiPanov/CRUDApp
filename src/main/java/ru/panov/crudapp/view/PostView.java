package ru.panov.crudapp.view;

import ru.panov.crudapp.controller.PostController;
import ru.panov.crudapp.model.Label;
import ru.panov.crudapp.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView {
    private final PostController postController;
    private final Scanner scanner;

    public PostView(PostController postController) {
        this.postController = postController;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("1. Create Post");
            System.out.println("2. Get Post by ID");
            System.out.println("3. Get All Posts");
            System.out.println("4. Update Post");
            System.out.println("5. Delete Post");
            System.out.println("6. Add label to Post");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1" -> create();
                case "2" -> get();
                case "3" -> getAll();
                case "4" -> update();
                case "5" -> delete();
                case "6" -> addLabel();
                case "0" -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void create() {
        System.out.println("Enter content: ");
        String content = scanner.nextLine();
        List<Label> labels = new ArrayList<>();
        Post newPost = postController.create(content, labels);
        System.out.println();
        System.out.println(newPost);
        System.out.println();
    }

    private void get() {
        System.out.println("Enter Post id: ");
        Integer id = scanner.nextInt();
        Post post = postController.get(id);
        System.out.println("Post found: " + post);
    }

    public void getAll() {
        List<Post> posts = postController.getAll();
        for (Post post : posts) {
            System.out.println(post);
            System.out.println();
        }
    }

    public void update() {
        System.out.println("Enter Post id to update: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter update content: ");
        String content = scanner.nextLine();
        List<Label> labels = new ArrayList<>();
        Post updatePost = postController.update(id, content, labels);
        System.out.println();
        System.out.println(updatePost);
        System.out.println();
    }

    public void delete() {
        System.out.print("Enter post ID to delete: ");
        Integer id = scanner.nextInt();
        postController.delete(id);
        System.out.println();
        System.out.println("Post by id: " + id + " deleted.");
        System.out.println();
    }

    public void addLabel() {
        System.out.print("Enter Post ID to add a post to: ");
        Integer postId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter label name: ");
        String name = scanner.nextLine();
        Label newLabel = new Label(name);
        Post updatePost = postController.addLabel(postId, newLabel);
        System.out.println();
        System.out.println(updatePost);
        System.out.println();
    }
}
