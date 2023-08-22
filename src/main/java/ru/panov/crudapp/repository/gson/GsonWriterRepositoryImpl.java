package ru.panov.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.panov.crudapp.model.Status;
import ru.panov.crudapp.model.Writer;
import ru.panov.crudapp.repository.WriterRepository;
import ru.panov.crudapp.util.exception.NotFoundException;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GsonWriterRepositoryImpl extends AbstractGsonRepo<Writer> implements WriterRepository {
    private static final String path = "src/main/resources/writers.json";

    public GsonWriterRepositoryImpl(Gson gson) {
        super(gson);
    }

    @Override
    public Writer getById(Integer id) {
        return getAll().stream().filter(x -> x.getId().equals(id)).findFirst()
                .orElseThrow(() -> new NotFoundException("Writer not found get id: " + id));
    }

    @Override
    public List<Writer> getAll() {
        try (Reader reader = new FileReader(path)) {
            return gson.fromJson(reader, new TypeToken<List<Writer>>() {
            }.getType());
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    @Override
    public Writer save(Writer writer) {
        List<Writer> getAll = getAll();
        if (getAll == null) {
            getAll = new ArrayList<>();
        }
        writer.setId(nextId(getAll));
        getAll.add(writer);
        saveList(getAll, path);
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        List<Writer> getAll = getAll();
        List<Writer> updated = getAll.stream()
                .map(w -> {
                    if (w.getId().equals(writer.getId())) {
                        return writer;
                    }
                    return w;
                }).toList();
        saveList(updated, path);
        return writer;
    }

    @Override
    public void deleteById(Integer id) {
        Writer writer = getById(id);
        writer.setStatus(Status.DELETED);
        update(writer);
    }
}