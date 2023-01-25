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

    public void saveToDoList(ToDoList todoList) {
        // Convert ToDoList into ArrayList<String>
        ArrayList<String> todoStringList = todoList.getDataList();
        if (Files.notExists(this.path)) {
            // Create file in filepath
            try {
                Files.createFile(this.path);
            } catch (IOException e) {
                throw new DukeException("Oh no! Something went wrong when creating a save file");
            }

        }

        try {
            Files.write(this.path, todoStringList);
        } catch (Exception e) {
            throw new DukeException("Oh no! Something happened while saving this the to do list");
        }
    }

    public ToDoList loadToDoList() {
        List<String> lines = Collections.emptyList();

        if (Files.exists(this.path)) {
            try {
                lines = Files.readAllLines(this.path, StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new DukeException("Oh no! Something happened while loading the to do list");
            }
        }

        ToDoList res = new ToDoList();
        if (!lines.isEmpty()) {
            lines.forEach(line -> {
                // Split up the line
                String[] lineArr = line.split("\\|");

                // Get the type of Task
                String type = lineArr[0].trim();
                String status = lineArr[1].trim();
                String content = lineArr[2].trim();
                Task task;

                // Create task according to the type of task
                switch (type) {
                case "T":
                    task = new Todo(content);
                    break;
                case "D":
                    String by = lineArr[3].trim();
                    task = new Deadline(content, by);
                    break;
                case "E":
                    String fromTo = lineArr[3].trim();
                    String[] fromToArr = fromTo.split("-");
                    String from = fromToArr[0].trim();
                    String to = fromToArr[1].trim();
                    task = new Event(content, from, to);
                    break;
                default:
                    task = new Task(content);
                    break;
                }

                // Add created task into the to do list
                if (status.equals("1")) {
                    task.markAsDone();
                }
                res.createToDo(task);
            });
        }
        return res;
    }
}
