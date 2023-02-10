package duke;

import duke.exception.InvalidFormatException;
import duke.exception.LogFileLoadException;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.LinkedList;

public class TaskList {
    private final LinkedList<Task> tasks;
    private final Storage storage;
    private final Parser parser;

    /**
     * Constructs an empty Task List.
     *
     * @param storage a permanent storage that TaskList maintains
     */
    public TaskList (Parser parser, Storage storage) {
        tasks = new LinkedList<>();
        this.storage = storage;
        this.parser = parser;
    }

    /**
     * Adds tasks from the storage
     *
     * @throws FileNotFoundException log file cannot found
     * @throws InvalidFormatException log file cannot be understood
     */
    public void loadFromStorage() throws FileNotFoundException, LogFileLoadException {
        storage.retrieveContents()
                .stream()
                .map(x -> createTask(x))
                .forEach(x -> tasks.add(x));
    }

    public Task createTask(String row) throws LogFileLoadException {
        try {
            Task output =  Task.factoryMethod(
                    row.charAt(1),
                    row.charAt(4),
                    row.substring(7),
                    parser);
            return output;
        } catch (InvalidFormatException exception) {
            throw new LogFileLoadException("error");
        }
    }

    /**
     * Add a task to the task list.
     *
     * @param t task to be added
     * @return an update of the task list
     */
    public String add(Task t) {
        tasks.add(t);
        return getStatus("Got it. I've added this task:", t);
    }

    /**
     * Delete a chosen task from the task list
     *
     * @param index the index of the chosen task
     * @param text string that prepends return the status message
     * @return An update of the changes to the task list
     */
    public String delete(int index, String text) {
        Task t = tasks.remove(index - 1);
        return getStatus(text, t);
    }

    /**
     * Call the setter method of a task in the task list
     *
     * @param index the index of the chosen task
     * @param done new done value of the task
     * @param text string that prepends return the status message
     * @return An update of the changes to the task list
     */
    public String setDone(int index, boolean done, String text) {
        Task t = tasks.get(index - 1);
        t.setDone(done);
        return getStatus(text, t);
    }

    /**
     * Returns a string of tasks that contain a certain keyword
     *
     * @param keyword String used to find matches
     * @return String of matches
     */
    public String find(String keyword) {
        int i = 0;
        String tmp = "";
        for (Task curr : tasks) {
            if (curr.toString().contains(keyword)) {
                tmp = tmp + "\n" + curr.toString();
            }
            i++;
        }
        return i == 0
                ? "There were no tasks with that keyword"
                : "Here are the matching tasks in your list:" + tmp;
    }

    /**
     * Returns a string representation of the Task List.
     * Each task has its own line, and is shown with its index
     *
     * @return a string representation of the Task List
     */
    @Override
    public String toString() {
        String output= "";

        int n = 1 + (int) Math.floor(Math.log10(tasks.size()));
        int i = 0;
        for (Task task : tasks) {
            output += String.format("%" + n + "d", i + 1).replace(' ', '0')
                    + String.format(".%s\n", task.toString());
            i++;
        }
        return output;
    }

    /**
     * Returns a String showing the status of the Task List
     *
     * @param text a string that prepends the status message
     * @param t Prints out the name of the task
     */
    private String getStatus(String text, Task t) {
        try {
            updateLogFile();
            return String.format("%s\n%s\nNow you have %d task(s) in the list.", text, t.toString(), tasks.size());
        } catch (IOException e) {
            return "Error: No permissions to edit log file";
        }
    }

    private void updateLogFile() throws IOException {
        LinkedList<String> texts = new LinkedList<>();
        for (Task t : tasks) {
            texts.add(t.toString(parser));
        }
        storage.update(texts);
    }
}
