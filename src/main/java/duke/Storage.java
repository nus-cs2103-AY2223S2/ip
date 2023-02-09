package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the file to store tasks the user has inputted.
 */
public class Storage {
    private static final String DEFAULT_FILEPATH = "./data/duke.txt";

    /**
     * Creates a storage file at the intended file path.
     */
    public Storage() {
    }

    /**
     * Saves user-inputted tasks into a file.
     *
     * @param tasks to be saved
     * @throws IOException if file cannot be accessed
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(DEFAULT_FILEPATH);
        StringBuilder textToAdd = new StringBuilder();
        for (Task task : tasks) {
            textToAdd.append(task).append("\n");
        }
        fw.write(String.valueOf(textToAdd));
        fw.close();
    }

    /**
     * Retrieves a Todo task from a line in the storage file.
     * @return a Todo task previously saved in the file.
     *
     * @throws IOException if file cannot be read
     * @throws DukeException if user input cannot be understood
     */
    private Task retrieveTodo(String taskDescriptor, boolean isDone) {
        Task task = new Todo(taskDescriptor.trim());
        if (isDone) {
            task.mark();
        }
        return task;
    }

    /**
     * Retrieves a Deadline task from a line in the storage file.
     * @return a Deadline task previously saved in the file.
     *
     * @throws IOException if file cannot be read
     * @throws DukeException if user input cannot be understood
     */
    private Task retrieveDeadline(String taskDescriptor, boolean isDone) throws DukeException {
        String[] restStrings = taskDescriptor.split("by:", 2);
        String description = restStrings[0].replaceAll("\\(", "").trim();
        String by = restStrings[1].replaceAll("\\)", "").trim();
        Task task = new Deadline(description, by);
        if (isDone) {
            task.mark();
        }
        return task;
    }

    /**
     * Retrieves a Event task from a line in the storage file.
     * @return a Event task previously saved in the file.
     *
     * @throws IOException if file cannot be read
     * @throws DukeException if user input cannot be understood
     */
    private Task retrieveEvent(String taskDescriptor, boolean isDone) {
        String[] restStrings = taskDescriptor.split("from:", 2);
        String description = restStrings[0].replaceAll("\\(", "").trim();
        String[] duration = restStrings[1].split("to:", 2);
        String from = duration[0].trim();
        String to = duration[1].replaceAll("\\)", "").trim();
        Task task = new Event(description, from, to);
        if (isDone) {
            task.mark();
        }
        return task;
    }

    /**
     * Retrieves tasks previously saved in a file for the user.
     * @return ArrayList of Tasks that were saved previously
     *
     * @throws IOException if file cannot be read.
     * @throws DukeException if user input cannot be understood.
     */
    public ArrayList<Task> retrieveTasks() throws IOException, DukeException {
        File f = new File(DEFAULT_FILEPATH);
        f.getParentFile().mkdirs();
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner scanner = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] inputs = line.split("]", 3);
            String taskType = inputs[0];
            String status = inputs[1];
            boolean isDone = status.equals("[X");
            String rest = inputs[2];
            Task task;

            if (taskType.equals("[T")) {
                task = retrieveTodo(rest, isDone);
                tasks.add(task);
            } else if (taskType.equals("[D")) {
                task = retrieveDeadline(rest, isDone);
                tasks.add(task);
            } else {
                task = retrieveEvent(rest, isDone);
                tasks.add(task);
            }
        }
        return tasks;
    }
}
