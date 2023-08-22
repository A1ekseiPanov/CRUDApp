package ru.panov.crudapp.model;

import java.time.LocalDateTime;
import java.util.List;

public class Post extends AbstractBaseEntity {
    private String content;
    private final LocalDateTime created;
    private LocalDateTime updated;
    private List<Label> labels;

    public Post(String content, List<Label> labels) {
        this.content = content;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
        this.labels = labels;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", labels=" + labels +
                '}';
    }
}