package ru.panov.crudapp.controller;

import ru.panov.crudapp.model.Post;
import ru.panov.crudapp.model.Writer;
import ru.panov.crudapp.repository.PostRepository;
import ru.panov.crudapp.repository.WriterRepository;

import java.util.List;

public class WriterController {
    private final WriterRepository writerRepository;
    private final PostRepository postRepository;

    public WriterController(WriterRepository writerRepository, PostRepository postRepository) {
        this.writerRepository = writerRepository;
        this.postRepository = postRepository;
    }

    public List<Writer> getAll() {
        return writerRepository.getAll();
    }

    public Writer get(Integer id) {
        return writerRepository.getById(id);
    }

    public Writer create(String firstName, String lastName, List<Post> posts) {
        Writer newWriter = new Writer(firstName, lastName, posts);
        return writerRepository.save(newWriter);
    }

    public void delete(Integer id) {
        writerRepository.deleteById(id);
    }

    public Writer update(Integer id, String firstName, String lastName, List<Post> posts) {
        Writer newWriter = new Writer(firstName, lastName, posts);
        newWriter.setId(id);
        return writerRepository.update(newWriter);
    }

    public Writer addPost(Integer writerId, Post post) {
        Writer writer = writerRepository.getById(writerId);
        writer.addPost(postRepository.save(post));

        return writerRepository.update(writer);
    }
}
