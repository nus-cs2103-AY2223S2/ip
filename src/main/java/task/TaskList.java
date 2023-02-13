package task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                    String.format("Only have %d things. FAILURE.", this.tasks.size()), null);
        }
    }

    /**
     * Prints the size of the task list.
     */
    public String getSizeAsFormattedString() {
        int size = this.tasks.size();
        if (size > 5) {
            return String.format("Haiya now %d things to do... Why so many? Why so weak?", size);
        }
        return String.format("Good, good. Now only have %d things to do.", size);
    }

    /**
     * Adds a Task to the task list.
     * @param task The task to be added.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return "OK OK Uncle Roger add for you:\n" + task + "\n" + this.getSizeAsFormattedString();
    }

    /**
     * Deletes the task from the list if the index exists.
     * @param index Index of the task to be deleted.
     */
    public String deleteTask(int index) {
        this.isInList(index);
        String finalString = "Haiya so weak delete task:\n" + this.tasks.get(index);
        this.tasks.remove(index);
        return finalString;
    }

    /**
     * Filters items in the list.
     * @param taskPredicate A filter that returns a boolean.
     * @return A filtered list of items.
     */
    private List<Task> filterItems(Predicate<Task> taskPredicate) {
        return this.tasks.stream().filter(taskPredicate).collect(Collectors.toList());
    }

    /**
     * Prints out all tasks in the list.
     */
    public String listItems() {
        return this.listItems((t) -> true);
    }

    /**
     * Prints out some items from the list.
     * @param taskPredicate A filter that returns a boolean.
     */
    public String listItems(Predicate<Task> taskPredicate) {
        if (this.isEmpty()) {
            return "Haiya list empty!";
        }

        String response = "OK OK Uncle Roger tell you what to do:\n";
        List<Task> filteredTasks = this.filterItems(taskPredicate);
        response += IntStream.range(0, filteredTasks.size())
                .mapToObj(i -> String.format("%d. %s\n", i + 1, filteredTasks.get(i)))
                .collect(Collectors.joining());
        return response;
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
        return String.format("OK OK Uncle Roger %smark for you:\n", isToMark ? "" : "un") + task;
    }

    /**
     * Gets the string used for storage.
     * @param delimiter How to separate the different fields of a task.
     * @return The string representation of the task list in storage.
     */
    public String tasksStorageString(String delimiter) {
        assert !delimiter.equals("");
        StringBuilder finalString = new StringBuilder();
        for (Task task : this.tasks) {
            finalString.append(task.toStorageString());
            finalString.append(delimiter);
        }
        return finalString.toString();
    }
}
