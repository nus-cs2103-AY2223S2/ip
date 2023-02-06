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
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

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
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] args = scanner.nextLine().split(" \\| ");
                checkTaskFormat(args[0], args[1]);
                switch (args[0]) {
                case "T":
                    Todo newTodo = new Todo(args[3]);
                    checkMarkFromFile(newTodo, args[1]);
                    setReminderFromFile(newTodo, args[2]);
                    loadedList.add(newTodo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(args[3], LocalDateTime.parse(args[4], formatter));
                    checkMarkFromFile(newDeadline, args[1]);
                    setReminderFromFile(newDeadline, args[2]);
                    loadedList.add(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(args[3], LocalDateTime.parse(args[4], formatter),
                            LocalDateTime.parse(args[5], formatter));
                    checkMarkFromFile(newEvent, args[1]);
                    setReminderFromFile(newEvent, args[2]);
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
     * Checks if the task is marked from the data file.
     * @param task The task to be checked.
     * @param mark Whether the task is marked.
     */
    public void checkMarkFromFile(Task task, String mark) {
        if (mark.equals("1")) {
            task.toggleMark();
        }
    }
    /**
     * Set the reminder of the task from the data file if it exists.
     * @param task The task to set the reminder.
     * @param reminder The reminder of the task.
     */
    public void setReminderFromFile(Task task, String reminder) {
        if (!reminder.equals("")) {
            task.setReminder(LocalDateTime.parse(reminder, formatter));
        }
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
                String reminder = task.getReminder() != null ? task.getReminder().toString() : "";
                String newString = String.format("T | %d | %s | %s%n", mark, reminder,
                            task.getDescription());
                if (task instanceof Deadline) {
                    newString = String.format("D | %d | %s | %s | %s%n", mark, reminder,
                            task.getDescription(), ((Deadline) task).getBy());
                } else if (task instanceof Event) {
                    newString = String.format("E | %d | %s | %s | %s | %s%n", mark, reminder,
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
