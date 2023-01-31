package fea.storage;


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

import fea.exceptions.FeaException;
import fea.task.Deadline;
import fea.task.Event;
import fea.task.Task;
import fea.task.Todo;
/**
 * Storage class that handles the loading and saving of tasks.
 */

public class Storage {
    private static final String FILE_NOT_FOUND = "Data file could not be found.";
    private Path filePath;
    private final String fileNotCreated = String.format("File could not be created at %s", filePath);

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }
    /**
     * Loads the tasks from the data file into the task list.
     *
     * @return ArrayList Returns an ArrayList containing the tasks from the data file.
     * @throws FeaException If the data file could not be found.
     */
    public ArrayList<Task> loadTasks() throws FeaException {
        File file = this.filePath.toFile();
        if (!file.exists()) {
            throw new FeaException(FILE_NOT_FOUND);
        }
        ArrayList<Task> loadedList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] args = scanner.nextLine().split(" \\| ");
                checkTaskFormat(args[0], args[1]);
                switch (args[0]) {
                case "T":
                    Todo newTodo = new Todo(args[2]);
                    if (args[1].equals("1")) {
                        newTodo.toggleMark();
                    }
                    loadedList.add(newTodo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(args[2], LocalDateTime.parse(args[3], formatter));
                    if (args[1].equals("1")) {
                        newDeadline.toggleMark();
                    }
                    loadedList.add(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(args[2], LocalDateTime.parse(args[3], formatter),
                            LocalDateTime.parse(args[4], formatter));
                    if (args[1].equals("1")) {
                        newEvent.toggleMark();
                    }
                    loadedList.add(newEvent);
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new FeaException(FILE_NOT_FOUND);
        }
        return loadedList;
    }
    /**
     * Saves the tasks in the tasklist into the data file.
     *
     * @param tasks The task list converted to an ArrayList to be saved.
     * @throws FeaException If the data file could not be found or there was an error creating the data file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws FeaException {
        File file = filePath.toFile();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new FeaException(fileNotCreated);
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
            throw new FeaException(FILE_NOT_FOUND);
        }
    }

    /**
     * Checks if the task format is correct to be stored in the data file.
     * @param taskType The type of task.
     * @param taskMark The mark of the task.
     */
    private void checkTaskFormat(String taskType, String taskMark) {
        boolean isValidTaskType = taskType.matches("[TDE]");
        assert(isValidTaskType) : "Invalid task type";
        boolean isValidTaskMark = taskMark.matches("[01]");
        assert(isValidTaskMark) : "Invalid task mark";
    }
}
