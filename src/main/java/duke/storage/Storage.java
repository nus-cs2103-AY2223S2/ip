package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        File file = this.filePath.toFile();
        if (!file.exists()) {
            throw new DukeException("Data file could not be found.");
        }
        ArrayList<Task> loadedList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] args = scanner.nextLine().split(" \\| ");
                if ("T".equals(args[0])) {
                    Todo newTodo = new Todo(args[2]);
                    if (args[1].equals("1")) {
                        newTodo.toggleMark();
                    }
                    loadedList.add(newTodo);
                } else if ("D".equals(args[0])) {
                    Deadline newDeadline = new Deadline(args[2], LocalDateTime.parse(args[3], formatter));
                    if (args[1].equals("1")) {
                        newDeadline.toggleMark();
                    }
                    loadedList.add(newDeadline);
                } else if ("E".equals(args[0])) {
                    Event newEvent = new Event(args[2], LocalDateTime.parse(args[3], formatter),
                            LocalDateTime.parse(args[4], formatter));
                    if (args[1].equals("1")) {
                        newEvent.toggleMark();
                    }
                    loadedList.add(newEvent);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Data file could not be found.");
        }
        return loadedList;
    }

    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        File file = filePath.toFile();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException(String.format("File could not be created at %s", filePath));
            }
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Task task : tasks) {
                int mark = task.getMark().equals('X') ? 1 : 0;
                String newString = String.format("T | %d | %s%n", mark, task.getDescription());
                if (task instanceof Deadline) {
                    newString = String.format("D | %d | %s | %s%n", mark,
                            task.getDescription(), ((Deadline) task).getBy());
                } else if (task instanceof Event) {
                    newString = String.format("E | %d | %s | %s | %s%n", mark,
                            task.getDescription(), ((Event) task).getFrom(), ((Event) task).getTo());
                }
                fileWriter.write(newString);
            }
        } catch (IOException e) {
            throw new DukeException("Data file could not be found.");
        }
    }
}
