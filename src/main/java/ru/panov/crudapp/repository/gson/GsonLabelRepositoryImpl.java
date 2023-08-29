package ru.panov.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.panov.crudapp.model.Label;
import ru.panov.crudapp.model.Post;
import ru.panov.crudapp.model.Status;
import ru.panov.crudapp.repository.LabelRepository;
import ru.panov.crudapp.repository.PostRepository;
import ru.panov.crudapp.util.exception.NotFoundException;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GsonLabelRepositoryImpl extends AbstractGsonRepo<Label> implements LabelRepository {
    private final String labelPath = "src/main/resources/labels.json";
    private final PostRepository postRepository;

    public GsonLabelRepositoryImpl(Gson gson, PostRepository postRepository) {
        super(gson);
        this.postRepository = postRepository;
    }

    @Override
    public Label getById(Integer id) {
        return getAll().stream().filter(x -> x.getId().equals(id)).findFirst()
                .orElseThrow(() -> new NotFoundException("Label not found get by id: " + id));
    }

    @Override
    public List<Label> getAll() {
        try (Reader reader = new FileReader(labelPath)) {
            List<Label> labels = gson.fromJson(reader, new TypeToken<List<Label>>() {
            }.getType());
            if (labels == null) {
                labels = new ArrayList<>();
            }
            return labels;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    @Override
    public Label save(Label label) {
        List<Label> getAll = getAll();
        label.setId(nextId(getAll));
        getAll.add(label);
        saveList(getAll, labelPath);
        return label;
    }

    @Override
    public Label update(Label label) {
        List<Label> getAll = getAll();
        List<Label> updated = getAll.stream()
                .map(l -> {
                    if (l.getId().equals(label.getId())) {
                        return label;
                    }
                    return l;
                }).toList();
        for (Post p : postRepository.getAll()) {
            if (p.getLabels().equals(getAll)) {
                p.setLabels(updated);
                postRepository.update(p);
            }
        }
        saveList(updated, labelPath);
        return label;
    }

    @Override
    public void deleteById(Integer id) {
        Label label = getById(id);
        label.setStatus(Status.DELETED);
        update(label);
    }
}