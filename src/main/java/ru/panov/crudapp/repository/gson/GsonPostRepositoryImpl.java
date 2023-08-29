package ru.panov.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.panov.crudapp.model.Post;
import ru.panov.crudapp.model.PostStatus;
import ru.panov.crudapp.model.Writer;
import ru.panov.crudapp.repository.PostRepository;
import ru.panov.crudapp.repository.WriterRepository;
import ru.panov.crudapp.util.exception.NotFoundException;

import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GsonPostRepositoryImpl extends AbstractGsonRepo<Post> implements PostRepository {
    private final String postPath = "src/main/resources/posts.json";
    private final WriterRepository writerRepository;

    public GsonPostRepositoryImpl(Gson gson, WriterRepository writerRepository) {
        super(gson);
        this.writerRepository = writerRepository;
    }

    @Override
    public Post getById(Integer id) {
        return getAll().stream().filter(x -> x.getId().equals(id)).findFirst()
                .orElseThrow(() -> new NotFoundException("Post not found get by id: " + id));
    }

    @Override
    public List<Post> getAll() {
        try (Reader reader = new FileReader(postPath)) {
            List<Post> posts = gson.fromJson(reader, new TypeToken<List<Post>>() {
            }.getType());
            if (posts == null) {
                posts = new ArrayList<>();
            }
            return posts;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    @Override
    public Post save(Post post) {
        List<Post> getAll = getAll();
        post.setId(nextId(getAll));
        getAll.add(post);
        saveList(getAll, postPath);
        return post;
    }

    @Override
    public Post update(Post post) {
        List<Post> getAll = getAll();
        List<Post> updated = getAll.stream()
                .map(p -> {
                    if (p.getId().equals(post.getId())) {
                        post.setCreated(p.getCreated());
                        post.setUpdated(LocalDateTime.now());
                        return post;
                    }
                    return p;
                }).toList();
        saveList(updated, postPath);
        for (Writer w : writerRepository.getAll()) {
            if (w.getPosts().equals(getAll)) {
                w.setPosts(updated);
                writerRepository.update(w);
            }
        }
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        Post post = getById(id);
        post.setPostStatus(PostStatus.DELETED);
        update(post);
    }
}