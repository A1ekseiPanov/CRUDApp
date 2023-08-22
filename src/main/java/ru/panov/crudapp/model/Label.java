package ru.panov.crudapp.model;

public class Label extends AbstractBaseEntity {
    private String name;

    public Label(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                '}';
    }
}