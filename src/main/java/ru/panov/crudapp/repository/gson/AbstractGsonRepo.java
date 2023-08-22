package ru.panov.crudapp.repository.gson;


import com.google.gson.Gson;
import ru.panov.crudapp.model.AbstractBaseEntity;

import java.io.FileWriter;
import java.util.List;

public abstract class AbstractGsonRepo<T extends AbstractBaseEntity> {
    protected final Gson gson;

    protected AbstractGsonRepo(Gson gson) {
        this.gson = gson;
    }

    protected void saveList(List<T> list, String path) {
        try (FileWriter out = new FileWriter(path)) {
            gson.toJson(list, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Integer nextId(List<T> list) {
        return list.stream().mapToInt(AbstractBaseEntity::getId).max().orElse(0) + 1;
    }
}