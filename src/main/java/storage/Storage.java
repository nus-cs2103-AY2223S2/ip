package storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exception.DukeException;
import exception.InvalidArgumentException;

/**
 * Represents the Storage class that handles saving and loading the task list
 */
public class Storage {
    private Path path;

    public Storage() {
        this.path = Paths.get("./data/duke.txt");
    }

    /**
     * Handles saving the task list to local storage
     * @param todoList The task list to be saved
     */
    public void saveTaskList(TaskList todoList) {
        // Convert TaskList into ArrayList<String>
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

    /**
     * Handles loading the task list to local storage
     */
    public TaskList loadTaskList() {
        List<String> lines = readFile();
        TaskList res = new TaskList();
        if (!lines.isEmpty()) {
            lines.forEach((String line) -> {
                String[] lineArr = line.split("\\|");
                String type = lineArr[0].trim();
                String status = lineArr[1].trim();
                String content = lineArr[2].trim();
                Task task;
                switch (type) {
                case "T":
                    task = new Todo(content);
                    break;
                case "D":
                    task = parseAndCreateDeadline(lineArr, content);
                    break;
                case "E":
                    task = parseAndCreateEvent(lineArr, content);
                    break;
                default:
                    task = null;
                    break;
                }
                if (status.equals("1")) {
                    task.markAsDone();
                }
                res.createToDo(task);
            });
        }
        return res;
    }

    /**
     * Parses the strings to create a new Event
     * @param lineArr the input line
     * @param content the contents of the event description
     * @return a newly created event
     * @throws InvalidArgumentException thrown if the format of the dates are wrong
     */
    public Event parseAndCreateEvent(String[] lineArr, String content) throws InvalidArgumentException {
        String from = lineArr[3].trim();
        String to = lineArr[4].trim();

        // Try to create LocalDate object from String
        LocalDate fromDate;
        LocalDate toDate;
        try {
            fromDate = LocalDate.parse(from);
            toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Date format should be in the format "
                    + "YYYY-MM-DD (e.g. 2007-12-03)");
        }

        return new Event(content, fromDate, toDate);
    }

    /**
     * Parses the strings to create a new Deadline
     * @param lineArr the input line
     * @param content the contents of the deadline description
     * @return a newly created deadline
     * @throws InvalidArgumentException thrown if the format of the dates are wrong
     */
    public Deadline parseAndCreateDeadline(String[] lineArr, String content) {
        String by = lineArr[3].trim();
        // Try to create LocalDate object from String
        LocalDate byDate;
        try {
            byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Date format should be in the format "
                    + "YYYY-MM-DD (e.g. 2007-12-03)");
        }
        return new Deadline(content, byDate);
    }

    /**
     * Function to read file
     * @return returns a list of Strings from the file to be read
     */
    public List<String> readFile() {
        List<String> lines = Collections.emptyList();

        if (Files.exists(this.path)) {
            try {
                lines = Files.readAllLines(this.path, StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new DukeException("Oh no! Something happened while loading the to do list");
            }
        }
        return lines;
    }
}
