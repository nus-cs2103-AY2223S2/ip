package storage;

import exception.DukeException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocalStorage {
    private Path path;

    public LocalStorage() {
        this.path = Paths.get("./data/duke.txt");
    }

    public void saveToDoList(ArrayList<String> todoList) {
        try {
            Files.write(this.path, todoList);
        } catch (Exception e) {
            throw new DukeException("Oh no! Something happened while saving this the to do list");
        }
    }

    public ArrayList<String> loadToDoList() {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(this.path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("Oh no! Something happened while saving this the to do list");
        }
        return new ArrayList<>(lines);
    }
}
