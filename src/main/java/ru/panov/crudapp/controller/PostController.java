package ru.panov.crudapp.controller;

import ru.panov.crudapp.model.Label;
import ru.panov.crudapp.model.Post;
import ru.panov.crudapp.repository.LabelRepository;
import ru.panov.crudapp.repository.PostRepository;

import java.util.List;

public class PostController {
    private final PostRepository postRepository;
    private final LabelRepository labelRepository;

    public PostController(PostRepository postRepository, LabelRepository labelRepository) {
        this.postRepository = postRepository;
        this.labelRepository = labelRepository;
    }

    public List<Post> getAll() {
        return postRepository.getAll();
    }

    public Post get(Integer id) {
        return postRepository.getById(id);
    }

    public Post create(String content, List<Label> labels) {
        Post newPost = new Post(content, labels);
        return postRepository.save(newPost);
    }

    public void delete(Integer id) {
        postRepository.deleteById(id);
    }

    public Post update(Integer id, String content, List<Label> labels) {
        Post newPost = new Post(content, labels);
        newPost.setId(id);
        return postRepository.update(newPost);
    }

    public Post addLabel(Integer postId, Label label) {
        Post post = postRepository.getById(postId);
        post.addLabel(labelRepository.save(label));
        return postRepository.update(post);
    }
}
