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
     * @param tasks to be saved.
     * @throws IOException if file cannot be accessed.
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
     *
     * @return a Todo task previously saved in the file.
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
     *
     * @return a Deadline task previously saved in the file.
     */
    private Task retrieveDeadline(String taskDescriptor, boolean isDone) throws DukeException {
        String[] restStrings = taskDescriptor.split("by:", 2);
        assert restStrings.length == 2 : "Not enough arguments to create deadline task.";
        String description = restStrings[0].replaceAll("\\(", "").trim();
        String by = restStrings[1].replaceAll("\\)", "").trim();
        Task task = new Deadline(description, by);
        if (isDone) {
            task.mark();
        }
        return task;
    }

    /**
     * Retrieves an Event task from a line in the storage file.
     *
     * @return an Event task previously saved in the file.
     */
    private Task retrieveEvent(String taskDescriptor, boolean isDone) {
        String[] restStrings = taskDescriptor.split("from:", 2);
        assert restStrings.length == 2 : "Not enough arguments to create event task.";
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
        if (f.createNewFile()) {
            return new ArrayList<>();
        }

        Scanner scanner = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String firstLine = scanner.nextLine();
            String[] inputs = firstLine.split("]", 3);
            assert inputs.length == 3 : "Too few arguments; likely storage of tasks in file was not done correctly.";
            String taskType = inputs[0];
            String status = inputs[1];
            boolean isDone = status.equals("[X");
            String rest = inputs[2];
            Task task;

            String secondLine = scanner.nextLine();
            String[] priorityInputs = secondLine.split(" ", 2);
            assert priorityInputs.length == 2 : "Too few arguments; likely priority of tasks was not saved correctly.";
            String priorityLevel = priorityInputs[1];

            if (taskType.equals("[T")) {
                task = retrieveTodo(rest, isDone);
            } else if (taskType.equals("[D")) {
                task = retrieveDeadline(rest, isDone);
            } else {
                task = retrieveEvent(rest, isDone);
            }
            task.setPriority(priorityLevel);
            tasks.add(task);
        }
        return tasks;
    }
}
