package duke;

import duke.exception.InvalidFormatException;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.LinkedList;

public class TaskList {
    private static final String ADD_MSG = "Got it. I've added this task:";
    private static final String DELETE_MSG = "Noted. I've removed this task:";
    private static final String MARK_T_MSG = "Nice, I've marked this task as done:";
    private static final String MARK_F_MSG = "Ok, I've marked this task as not done:";
    private static final String FIND_SUCCESS_MSG = "Here are the matching tasks in your list:";
    private static final String FIND_FAILURE_MSG = "There were no tasks with that keyword";
    private static final String LIST_HEADER_MSG = "Here are all your tasks:";
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
     */
    public void loadFromStorage() throws FileNotFoundException, InvalidFormatException {
        for (String row : storage.retrieveContents()) {
            tasks.add(Task.factoryMethod(
                    row.charAt(1),
                    row.charAt(4),
                    row.substring(7),
                    parser
            ));
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
        return getStatus(ADD_MSG, t);
    }

    /**
     * Delete a chosen task from the task list
     *
     * @param index the index of the chosen task
     * @return An update of the changes to the task list
     */
    public String delete(int index) {
        Task t = tasks.remove(index - 1);
        return getStatus(DELETE_MSG, t);
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
     * Sets a task of given index to true
     *
     * @param index the index of the chosen task
     * @return An update of the changes to the task list
     */
    public String setDone(int index) {
        return setDone(index, true, MARK_T_MSG);
    }

    /**
     * Sets a task of given index to false
     *
     * @param index the index of the chosen task
     * @return An update of the changes to the task list
     */
    public String setNotDone(int index) {
        return setDone(index, false, MARK_F_MSG);
    }

    /**
     * Returns a string of tasks that contain a certain keyword
     *
     * @param keyword String used to find matches
     * @return String of matches
     */
    public String find(String keyword) {
        boolean success = false;
        StringBuilder output = new StringBuilder(FIND_SUCCESS_MSG);
        for (Task curr : tasks) {
            if (curr.toString().contains(keyword)) {
                output.append("\n");
                output.append(curr.toString());
                success = true;
            }
        }
        return success
            ? FIND_FAILURE_MSG
            : output.toString();
    }

    /**
     * Returns a string representation of the Task List.
     * Each task has its own line, and is shown with its index
     *
     * @return a string representation of the Task List
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(LIST_HEADER_MSG);
        int maxDigits = 1 + (int) Math.floor(Math.log10(tasks.size()));
        int index = 0;
        for (Task task : tasks) {

            output.append("\n");
            output.append(String.format("%" + maxDigits + "d", index + 1).replace(' ', '0'));
            output.append(task.toString());
            index++;
        }
        return output.toString();
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
            return "Warning: No permission to edit log file, changes will not persist between sessions";
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
