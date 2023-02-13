package task;

import exception.CommandNotFoundException;
import exception.InvalidCommandInputException;
import exception.InvalidDateFormatException;

import helper.DateTimeHelper;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the list of tasks used by the UI.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    /**
     * Adds a task to the list using the user input.
     *
     * @param input The user input.
     * @return The corresponding task object.
     * @throws CommandNotFoundException If command is invalid.
     * @throws InvalidCommandInputException If command argument is invalid.
     */
    public Task addTask(String input) throws CommandNotFoundException, InvalidCommandInputException {
        // Check for empty command or arguments.
        if (input != null && (input.equals("todo") || input.equals("deadline") || input.equals("event"))) {
            throw new InvalidCommandInputException("Empty argument", input);
        } else if (input.matches("deadline .* /by .*")) {
            addDeadline(input);
        } else if (input.matches("event .* /from .* /to .*")) {
            addEvent(input);
        } else if (input.matches("todo .*")) {
            addTodo(input);
        } else {
            throw new CommandNotFoundException("Duke command is invalid.", input);
        }

        // Return the added task (last element).
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Adds a variable number of task objects to the task list.
     *
     * @param t The task object to be added.
     */
    public void addTask(Task ... t) {
        for (Task task: t) {
            tasks.add(task);
        }
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param input The string to be parsed.
     */
    public void addDeadline(String input) throws InvalidCommandInputException {

        String[] arr = input.split(" /by ");
        String content = arr[0].substring(9);

        if (content.length() == 0 || arr[1].length() == 0) {
            throw new InvalidCommandInputException("Empty argument", "deadline");
        }

        try {
            tasks.add(new Deadline(content, arr[1]));
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a todo task to the list.
     *
     * @param input The string to be parsed.
     */
    public void addTodo(String input) throws InvalidCommandInputException {
        if (input.length() == 5) {
            throw new InvalidCommandInputException("Empty argument", "todo");
        }

        tasks.add(new ToDo(input.substring(5)));
    }

    /**
     * Adds an event task to the list.
     *
     * @param input The string to be parsed.
     */
    public void addEvent(String input) throws InvalidCommandInputException {

        String[] arr = input.split(" /from ");
        String content = arr[0].substring(6, arr[0].length());
        String[] startEnd = arr[1].split(" /to ");

        if (content.length() == 0 || startEnd[0].length() == 0 || startEnd[1].length() == 0) {
            throw new InvalidCommandInputException("Empty argument", "event");
        }

        try {
            tasks.add(new Event(content, startEnd[0], startEnd[1]));
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates a task in the list.
     *
     * @param i Index to update.
     * @param input Content to update.
     * @return The newly updated task.
     * @throws InvalidCommandInputException
     */
    public Task updateTask(int i, String input) throws InvalidCommandInputException {
        Task res = getTask(i);
        res.update(input);

        return res;
    }

    /**
     * Deletes a task at the given index.
     *
     * @param i Index of the task to be deleted.
     */
    public Task deleteTask(int i) {
        Task res = this.getTask(i);
        tasks.remove(i);

        return res;
    }

    /**
     * Marks a task at the given index.
     *
     * @param i Index of the task to be marked.
     */
    public void markTask(int i) {
        tasks.get(i).mark();
    }

    /**
     * Unmarks a task at the given index.
     *
     * @param i Index of the task to be unmarked.
     */
    public void unmarkTask(int i) {
        tasks.get(i).unmark();
    }

    /**
     * Gets a task at the given index.
     *
     * @param i Index of the task.
     * @return The task object to be returned.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Gets all tasks as a list.
     *
     * @return The task object to be returned.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int numberOfTasks() {
        return tasks.size();
    }

    /**
     * Print the tasks that occurs on a given date. It is used for the occurs command.
     *
     * @param input The date to check.
     * @throws InvalidDateFormatException If the date given does not follow the specified format.
     */
    public TaskList findTasksOnDate(String input) throws InvalidDateFormatException {

        LocalDateTime datetime = DateTimeHelper.parse(input);

        TaskList result = new TaskList();

        // Find all valid tasks that occur on the deadline or within the Event period.
        for (Task t: tasks) {
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                if (d.occursOn(datetime)) {
                    result.addTask(t);
                }
            } else if (t instanceof Event) {
                Event e = (Event) t;
                if (e.occursOn(datetime)) {
                    result.addTask(t);
                }
            }
        }

        return result;
    }

    /**
     * Prints out the tasks containing a given word.
     *
     * @param word The word to check.
     */
    public TaskList findTasksWithWord(String word) {

        TaskList result = new TaskList();

        for (Task t: tasks) {
            if (t.containsWord(word)) {
                result.addTask(t);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        String result = "";
        Integer curr = 1;

        for (Task t: tasks) {
            result += curr.toString() + ". " + t;

            if (curr != tasks.size()) {
                result += '\n';
            }

            curr++;
        }

        return result;
    }
}