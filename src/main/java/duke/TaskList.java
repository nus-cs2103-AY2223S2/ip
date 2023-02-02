package duke;

import duke.exception.InvalidFormatException;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class TaskList {
    private final Storage storage;
    protected static LinkedList<Task> tasks;

    /**
     * An abstraction of the list of tasks
     *
     * @param storage For the TaskList to maintain
     */
    public TaskList (Storage storage) {
        this.storage = storage;
        tasks = new LinkedList<>();
    }

    /**
     * Load tasks from the log list, it does not override the current tasks, it appends them
     *
     * @param p A parser is required to process the input files
     */
    public void loadFromStorage(Parser p) throws FileNotFoundException, InvalidFormatException {
        for (String row : storage.retrieveContents()) {
            tasks.add(Task.factoryMethod(
                    row.charAt(1),
                    row.charAt(4),
                    row.substring(7),
                    p
            ));
        }
    }

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
     * Prints the status of the task list
     *
     * @param text Any message to prepend before the text
     * @param t Prints out the name of the task
     */
    public String getStatus(String text, Task t) {
        try {
            storage.update(tasks);
            return String.format("%s\n%s\nNow you have %d task(s) in the list.", text, t.toString(), tasks.size());
        } catch (IOException e) {
            return "Error: No permissions to read/write log file";
        }
    }

    /**
     * Add a task to task list.
     *
     * @param t Task to be added
     * @return Update of the task list
     */
    public String add(Task t) {
        tasks.add(t);
        return getStatus("Got it. I've added this task:", t);
    }

    /**
     * Set a task to done or not done, returns a String of the status
     *
     * @param index of the task in the task list
     * @param done boolean to set true or false
     * @param text Any text to prepend the output of the message
     * @return An update of the changes to the task list
     */
    public String setDone(int index, boolean done, String text) {
        Task t = tasks.get(index - 1);
        t.setDone(done);
        return getStatus(text, t);
    }

    /**
     * Delete a task from the tasklist
     *
     * @param index of the task
     * @param text Any text to prepend the output of the message
     * @return An update of the changes to the task list
     */
    public String delete(int index, String text) {
        Task t = tasks.remove(index - 1);
        return getStatus(text, t);
    }

    /**
     * Filters the tasks with a String
     *
     * @param keyword String used to find matches
     * @return List of matches
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
}
