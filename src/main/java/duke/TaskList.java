package duke;

import duke.exception.InvalidFormatException;
import duke.exception.LogFileLoadException;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * @param regex String used to find matches
     * @return String of matches
     */
    public String find(String regex) {
        Pattern pattern = Pattern.compile(regex);
        LinkedList<Task> foundMatches = new LinkedList<>();

        for (Task curr : tasks) {
            Matcher m = pattern.matcher(curr.toString());
            if (m.find()) {
                foundMatches.add(curr);
            }
        }

        if (foundMatches.size() == 0) {
            return FIND_FAILURE_MSG;
        }

        StringBuilder output = new StringBuilder(FIND_SUCCESS_MSG);
        for (Task curr : foundMatches) {
            output.append("\n");
            output.append(curr.toString());
        }
        return output.toString();
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
