package task;

import java.util.ArrayList;
import java.util.function.Function;

import dukeexception.NotFoundException;
import struct.Triple;

/**
 * Task list to contain the tasks that the user has to complete.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor for TaskList
     * @param triples Triple structs containing the task's type, mark status, and content.
     */
    public TaskList(ArrayList<Triple<Character, Boolean, String>> triples) {
        this.tasks = new ArrayList<>();
        for (Triple<Character, Boolean, String> triple : triples) {
            Task task = Task.create(triple.getFirst(), triple.getThird());
            if (task != null) {
                task.mark(triple.getSecond());
                this.tasks.add(task);
            }
        }
    }

    /**
     * Checks if the task list is empty.
     * @return Whether the task list is empty.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Checks whether the item with that id is in the list.
     * @param id The id of the potential item.
     */
    private void isInList(int id) {
        if (id >= this.tasks.size() || id < 0) {
            throw new NotFoundException("List",
                    String.format("Haiya we only got %d things lah.", this.tasks.size()), null);
        }
    }

    /**
     * Prints the size of the task list.
     */
    public String printSize() {
        return String.format("Now have %d items.%n", this.tasks.size());
    }

    /**
     * Adds a Task to the task list.
     * @param task The task to be added.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        StringBuilder finalString = new StringBuilder();
        finalString.append("OK I put in for you:\n" + task);
        this.printSize();
        return finalString.toString();
    }

    /**
     * Deletes the task from the list if the index exists.
     * @param index Index of the task to be deleted.
     */
    public String deleteTask(int index) {
        this.isInList(index);
        StringBuilder finalString = new StringBuilder();
        finalString.append("OK I take out for you:\n" + this.tasks.get(index));
        this.tasks.remove(index);
        this.printSize();
        return finalString.toString();
    }

    /**
     * Gets the task in the id spot.
     * @param id Index of the task to be retrieved.
     * @return The task at that index.
     */
    private Task getTask(int id) {
        return this.tasks.get(id);
    }

    /**
     * Prints out all tasks in the list.
     */
    public String listItems() {
        return this.listItems((t) -> true);
    }

    /**
     * Prints out some items from the list.
     * @param taskFilter A filter that returns a boolean.
     */
    public String listItems(Function<Task, Boolean> taskFilter) {
        if (this.isEmpty()) {
            return "Haiya list empty!";
        }

        StringBuilder finalString = new StringBuilder();
        finalString.append("Here's your list:\n");
        int count = 0;
        for (int id = 0; id < this.tasks.size(); id++) {
            Task task = this.getTask(id);
            if (taskFilter.apply(task)) {
                finalString.append(String.format("%d. %s\n", count + 1, task));
                count += 1;
            }
        }
        return finalString.toString();
    }

    /**
     * Marks or unmarks the task.
     * @param index The index of the task that needs to be marked.
     * @param isToMark Whether to mark or unmark the task.
     */
    public String markTask(int index, boolean isToMark) {
        this.isInList(index);
        Task task = this.tasks.get(index);
        task.mark(isToMark);
        return String.format("OK %smark for you already:\n", isToMark ? "" : "un") + task;
    }

    /**
     * Gets the string used for storage.
     * @param delimiter How to separate the different fields of a task.
     * @return The string representation of the task list in storage.
     */
    public String tasksStorageString(String delimiter) {
        StringBuilder finalString = new StringBuilder();
        for (Task task : this.tasks) {
            finalString.append(task.toStorageString());
            finalString.append(delimiter);
        }
        return finalString.toString();
    }
}
