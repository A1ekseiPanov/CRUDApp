package ru.panov.crudapp.view;

import ru.panov.crudapp.controller.LabelController;
import ru.panov.crudapp.model.Label;

import java.util.List;
import java.util.Scanner;

public class LabelView {
    private final LabelController labelController;
    private final Scanner scanner;

    public LabelView(LabelController labelController) {
        this.labelController = labelController;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("1. Create Label");
            System.out.println("2. Get Label by ID");
            System.out.println("3. Get All Labels");
            System.out.println("4. Update Label");
            System.out.println("5. Delete Label");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1" -> create();
                case "2" -> get();
                case "3" -> getAll();
                case "4" -> update();
                case "5" -> delete();
                case "0" -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void create() {
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        Label newLabel = labelController.create(name);
        System.out.println();
        System.out.println(newLabel);
        System.out.println();
    }

    public void get() {
        System.out.println("Enter Label id: ");
        Integer id = scanner.nextInt();
        Label label = labelController.get(id);
        System.out.println();
        System.out.println("Label found: " + label);
        System.out.println();
    }

    public void getAll() {
        List<Label> labels = labelController.getAll();
        for (Label label : labels) {
            System.out.println(label);
            System.out.println();
        }
    }

    public void update() {
        System.out.println("Enter Label id to update: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter update name: ");
        String name = scanner.nextLine();
        Label updateLabel = labelController.update(id, name);
        System.out.println();
        System.out.println(updateLabel);
        System.out.println();
    }

    public void delete() {
        System.out.print("Enter label ID to delete: ");
        Integer id = scanner.nextInt();
        labelController.delete(id);
        System.out.println();
        System.out.println("Label by id: " + id + " deleted.");
        System.out.println();
    }
}
