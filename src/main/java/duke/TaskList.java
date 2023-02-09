package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskList class that contains information about currently registered tasks.
 */
public class TaskList {

    protected ArrayList<Task> tasks;
    private int numOfTasks;

    /**
     * Constructor for TaskList mainly used by Storage to return loaded tasks.
     * Returns TaskList object with loaded tasks.
     *
     * @param loadedTasks ArrayList of Task with tasks loaded by Storage.
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.numOfTasks = loadedTasks.size();
        this.tasks = loadedTasks;
    }

    /**
     * Constructor for TaskList in very first initialization of Duke or when data file is corrupted or does not exist.
     * Returns empty TaskList object.
     */
    public TaskList() {
        this.numOfTasks = 0;
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints all tasks that are currently registered.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Marks specified task as done.
     *
     * @param arg Index starting from 1 for the specific task to be marked done.
     * @throws DukeException If arg is out of bounds or not an integer.
     */
    public void markTask(String arg) throws DukeException {
        try {
            int num = Integer.parseInt(arg);
            if (num > numOfTasks || num <= 0) {
                throw new DukeException("Task number is out of bounds!");
            }
            tasks.get(num - 1).markDone();
            System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(num - 1));
        } catch (NumberFormatException ex) {
            throw new DukeException("Invalid number");
        }
    }

    /**
     * Marks specified task as not done.
     *
     * @param arg Index starting from 1 for the specific task to be marked not done.
     * @throws DukeException If arg is out of bounds or not an integer.
     */
    public void unmarkTask(String arg) throws DukeException {
        try {
            int num = Integer.parseInt(arg);
            if (num > numOfTasks || num <= 0) {
                throw new DukeException("Task number is out of bounds!");
            }
            tasks.get(num - 1).markUndone();
            System.out.println("Ok, I've marked this task as not done yet:\n  " + tasks.get(num - 1));
        } catch (NumberFormatException ex) {
            throw new DukeException("Invalid number");
        }
    }

    /**
     * Method to merge parsed arguments into relevant string.
     * @param strings Parsed argument as array of strings.
     * @param from Index of first string to be merged.
     * @param to Index of the string that should not be merged (end index).
     * @return Merged string of arguments.
     */
    private String mergeStrings(String[] strings, int from, int to) {
        StringBuilder mergedStrings = new StringBuilder(strings[from]);
        for (int i = from + 1; i < to; i++) {
            mergedStrings.append(" ").append(strings[i]);
        }
        return mergedStrings.toString();
    }

    /**
     * Adds a Todo task into the TaskList.
     *
     * @param args Array of inputs for constructing Todo. Each element refers to each word of Todo's description.
     */
    public void addTodo(String[] args) {
        int len = args.length;
        String taskName = mergeStrings(args, 1, len);
        Todo todo = new Todo(taskName);
        tasks.add(todo);
        System.out.println("Got it. I've added this To Do:\n  " + tasks.get(numOfTasks));
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    /**
     * Adds a Deadline task into the TaskList.
     *
     * @param args Array of inputs for constructing Deadline task. First few elements up to {by} refers to each word of
     *         Deadline's description. Remaining elements refer to the deadline date.
     * @param by Index where the argument for LocalDate deadline begins.
     */
    public void addDeadline(String[] args, int by) throws DukeException {
        int len = args.length;
        String taskName = mergeStrings(args, 1, by);
        String byWhen = mergeStrings(args, by + 1, len);
        Deadline deadline = new Deadline(taskName, byWhen);
        tasks.add(deadline);
        System.out.println("Got it. I've added this Deadline:\n  " + tasks.get(numOfTasks));
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    /**
     * Adds an Event task into the TaskList.
     *
     * @param args Array of inputs for constructing Event task. First few elements up to from refers to each word of
     *         Event's description. Remaining elements up to {to} refers to start date of Event. Remaining elements
     *         refers to the end date of Event.
     * @param from Index where the argument for LocalDate start date begins.
     * @param to Index where the argument for LocalDate end date begins.
     */
    public void addEvent(String[] args, int from, int to) throws DukeException {
        int len = args.length;
        String taskName = mergeStrings(args, 1, from);
        String startDate = mergeStrings(args, from + 1, to);
        String endDate = mergeStrings(args, to + 1, len);
        Event event = new Event(taskName, startDate, endDate);
        tasks.add(event);
        System.out.println("Got it. I've added this Event:\n  " + tasks.get(numOfTasks));
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    /**
     * Deletes task in the TaskList specified by the argument.
     *
     * @param arg Index starting from 1 for the specific task to be deleted.
     * @throws DukeException If arg is out of bounds or is not an integer.
     */
    public void deleteTask(String arg) throws DukeException {
        try {
            int num = Integer.parseInt(arg);
            if (num > numOfTasks || num <= 0) {
                throw new DukeException("Task number is out of bounds!");
            }
            System.out.println("Noted. I've removed this task:\n  " + tasks.get(num - 1));
            tasks.remove(num - 1);
            numOfTasks--;
            System.out.println("Now you have " + (numOfTasks) + " tasks in the list.");
        } catch (NumberFormatException ex) {
            throw new DukeException("Invalid number");
        }
    }

    /**
     * Finds tasks with matching keyword amongst currently registered tasks.
     * Searches for matching tag if argument begins with '#'.
     *
     * @param arg String keyword to search for.
     */
    public void find(String arg) {
        boolean isTag = arg.charAt(0) == '#';
        String keyword = arg;
        if (isTag) {
            findTag(arg.substring(1));
        } else {
            findKeyword(arg);
        }
    }

    /**
     * Called by find method if searching for keyword.
     * @param keyWord Matching keyword to search for in TaskList.
     */
    private void findKeyword(String keyWord) {
        List<Task> foundTasks = tasks.stream().filter(task -> task.getDescription().contains(keyWord))
                .collect(Collectors.toList());
        if (foundTasks.size() == 0) {
            System.out.println("There are no tasks with matching keyword!");
        } else {
            System.out.println("Here are the tasks with matching keyword in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + ". " + foundTasks.get(i));
            }
        }
    }

    /**
     * Called by find method if searching for tag.
     * @param tagName Matching tag to search for in TaskList.
     */
    private void findTag(String tagName) {
        List<Task> foundTasks = tasks.stream().filter(task -> task.getTag().contains(tagName))
                .collect(Collectors.toList());
        if (foundTasks.size() == 0) {
            System.out.println("There are no tasks with matching tag!");
        } else {
            System.out.println("Here are the tasks with matching tag in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + ". " + foundTasks.get(i));
            }
        }
    }

    /**
     * Tags task at index with tagName.
     * @param index Index of task in TaskList.
     * @param tagName Name of tag.
     */
    public void tag(int index, String tagName) {
        tasks.get(index - 1).tagTask(tagName);
        System.out.println("Tagged task " + index + " with #" + tagName);
    }

    /**
     * Getter for number of tasks currently registered
     */
    public int getNumOfTasks() {
        return this.numOfTasks;
    }

}
