package duke;

import java.util.ArrayList;

public class TaskList {

    private int numOfTasks;
    protected ArrayList<Task> tasks;

    /**
     * Constructor for TaskList mainly used by Storage to return loaded tasks.
     * Returns TaskList object with loaded tasks.
     *
     * @param loadedTasks ArrayList<Task> of tasks loaded by Storage.
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
     * Adds a Todo task into the TaskList.
     *
     * @param args Array of inputs for constructing Todo. Each element refers to each word of Todo's description.
     */
    public void addTodo(String[] args) {
        int len = args.length;
        StringBuilder taskName = new StringBuilder(args[1]);
        for (int i = 2; i < len; i++) {
            taskName.append(" ").append(args[i]);
        }
        Todo todo = new Todo(taskName.toString());
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
        StringBuilder taskName = new StringBuilder(args[1]);
        for (int i = 2; i < by; i++) {
            taskName.append(" ").append(args[i]);
        }
        StringBuilder byWhen = new StringBuilder(args[by + 1]);
        for (int i = by + 2; i < len; i++) {
            byWhen.append(" ").append(args[i]);
        }
        Deadline deadline = new Deadline(taskName.toString(), byWhen.toString());
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
        StringBuilder taskName = new StringBuilder(args[1]);
        for (int i = 2; i < from; i++) {
            taskName.append(" ").append(args[i]);
        }
        StringBuilder fromWhen = new StringBuilder(args[from + 1]);
        for (int i = from + 2; i < to; i++) {
            fromWhen.append(" ").append(args[i]);
        }
        StringBuilder toWhen = new StringBuilder(args[to + 1]);
        for (int i = to + 2; i < len; i++) {
            toWhen.append(" ").append(args[i]);
        }
        Event event = new Event(taskName.toString(), fromWhen.toString(), toWhen.toString());
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
}
