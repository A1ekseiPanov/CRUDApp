package ru.panov.crudapp.model;

public class Label extends AbstractBaseEntity {
    private String name;
    private Status status;

    public Label(String name) {
        this.name = name;
        this.status = Status.ACTIVE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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