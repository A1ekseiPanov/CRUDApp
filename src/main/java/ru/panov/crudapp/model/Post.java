package ru.panov.crudapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post extends AbstractBaseEntity {
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;
    private List<Label> labels;
    private PostStatus postStatus;

    public Post(String content) {
        this.content = content;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
        this.postStatus = PostStatus.ACTIVE;
    }

    public Post(String content, List<Label> labels) {
        this.content = content;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
        this.labels = labels;
        this.postStatus = PostStatus.ACTIVE;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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

    public PostStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postStatus=" + postStatus +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", labels=" + labels +
                '}';
    }

    public void addLabel(Label label) {
        if (labels == null) {
            labels = new ArrayList<>();
        }
        labels.add(label);
    }
}