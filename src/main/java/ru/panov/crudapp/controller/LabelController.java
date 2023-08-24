package ru.panov.crudapp.controller;

import ru.panov.crudapp.model.Label;
import ru.panov.crudapp.repository.LabelRepository;

import java.util.List;

public class LabelController {
    public final LabelRepository labelRepository;

    public LabelController(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public List<Label> getAll() {
        return labelRepository.getAll();
    }

    public Label get(Integer id) {
        return labelRepository.getById(id);
    }

    public Label create(String name) {
        Label newLabel = new Label(name);
        return labelRepository.save(newLabel);
    }

    public void delete(Integer id) {
        labelRepository.deleteById(id);
    }

    public Label update(Integer id, String name) {
        Label newLabel = new Label(name);
        newLabel.setId(id);
        return labelRepository.update(newLabel);
    }
}
