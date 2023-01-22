package task;

import java.util.ArrayList;

import dukeexception.NotFoundException;
import struct.Triple;

/**
 * Task list for containing tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor for TaskList.
     * @param triples Tuples of data containing the task type, mark status, and task content.
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
        if (id >= this.tasks.size()) {
            throw new NotFoundException("List",
                    String.format("Haiya we only got %d things lah.", this.tasks.size()), null);
        }
    }

    /**
     * Prints the size of the task list.
     */
    public void printSize() {
        System.out.printf("Now have %d items.%n", this.tasks.size());
    }

    /**
     * Adds a Task to the task list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("OK I put in for you: " + task);
        this.printSize();
    }

    /**
     * Deletes the task from the list if the index exists.
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.isInList(index);
        System.out.println("OK I take out for you: " + this.tasks.get(index));
        this.tasks.remove(index);
        this.printSize();
    }

    /**
     * Prints the contents of the task.
     * @param id The id of the task to be printed.
     * @param withNumber Whether to print the task with its index.
     */
    public void printTask(int id, boolean withNumber) {
        String numbering = withNumber ? (id + 1) + ". " : "";
        System.out.println(numbering + this.tasks.get(id));
    }

    /**
     * Prints out all tasks in the list.
     */
    public void listItems() {
        if (this.isEmpty()) {
            System.out.println("List empty!");
            return;
        }

        System.out.println("Here's your list:");
        for (int id = 0; id < this.tasks.size(); id++) {
            this.printTask(id, true);
        }
    }

    /**
     * Marks or unmarks the task.
     * @param index The index of the task that needs to be marked.
     * @param isToMark Whether to mark or unmark the task.
     */
    public void markTask(int index, boolean isToMark) {
        this.isInList(index);
        Task task = this.tasks.get(index);
        task.mark(isToMark);
        System.out.println(String.format("OK %smark for you already: ", isToMark ? "" : "un") + task);
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
