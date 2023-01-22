package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import exceptions.DukeException;
import utils.Loader;

/**
 * TaskList represents a data structure that holds Tasks.
 */
public class TaskList {
    private final List<Task> taskList = new ArrayList<>();

    /**
     * Initializes a TaskList with preloaded data.
     *
     * @param preloader A Loader which loads data.
     */
    public TaskList(Loader<TaskList> preloader) throws DukeException {
        Boolean success = preloader.load(this);
        if (success) {
            System.out.println("Successfully loaded data.");
        }
    }

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {}

    /**
     * A generic filter function which parses through the TaskList and return tasks that match.
     *
     * @param predicate A boolean function.
     */
    public void filter(Predicate<? super Task> predicate, String emptyMsg) {
        List<Task> filteredList = taskList.stream().filter(predicate).collect(Collectors.toList());
        if (filteredList.size() == 0) {
            System.out.println(emptyMsg);
            return;
        }
        ListIterator<Task> it = filteredList.listIterator();
        while (it.hasNext()) {
              System.out.println(it.nextIndex() + 1 + ". " + it.next());
        }
    }

    /**
     * Adds a given task into the TaskList.
     *
     * @param task The task to be added.
     * @param print Boolean value indicating if console messages should be printed.
     */
    public void addTask(Task task, boolean print) {
        taskList.add(task);
        if (print) {
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + task);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    public void addTask(Task task) {
        addTask(task, true);
    }

    private boolean isValidKey(Integer key) {
        return (key <= taskList.size() && key > 0);
    }

    public Optional<Task> getTask(Integer key) throws DukeException {
        if (!isValidKey(key)) {
            throw new DukeException("This task don't exists! Please select one from the list.");
        }
        // accounts for 0-based indexing
        return Optional.of(taskList.get(key - 1));
    }

    /**
     * Deletes the given task.
     *
     * @param key The task identifier.
     */
    public void deleteTask(Integer key) throws DukeException {
        if (!isValidKey(key)) {
            throw new DukeException("This task don't exists! Please select one from the list.");
        }
        Task task = taskList.get(key - 1);
        taskList.remove(key - 1);
        System.out.println("Noted. I've removed the task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Lists the tasks in the TaskList.
     */
    public void listTasks(Predicate<? super Task> predicate) {
        this.filter(predicate, "There are no outstanding tasks!");
    }
}
