package duke.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import duke.store.Storage;

/**
 *      File name: duke.task.TaskList.java
 *      @author: Jerome Neo
 *      Description: duke.task.TaskList class putting Task objects into a list.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructor for the duke.task.TaskList object.
     */
    public TaskList() {

        tasks = new ArrayList<>();
    }

    /**
     * Returns an integer representing the size of the duke.task.TaskList.
     *
     * @return the size of the duke.task.TaskList.
     */
    public static Integer size() {
        return tasks.size();
    }

    /**
     * Adds a Task object into the duke.task.TaskList.
     *
     * @param task to be added.
     */
    public void addTask(Task task) throws IOException {
        this.tasks.add(task);
        TaskList.announceAdded(task);
        Storage.autoSave(this);
    }

    /**
     * Returns a Task object at a specific index.
     * @param i index.
     * @return Task object at index.
     */
    public Task getTaskAtIndex(Integer i) {
        return tasks.get(i);
    }

    /**
     * Identical to matchDescription() but returns string instead.
     * @param description
     * @return a String
     */
    public String matchDescriptionString(String description) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the task(s) matched in your list: \n");
        FuzzySearch search = new FuzzySearch();
        Integer numMatches = 0;
        // Keep track what of duplicates
        HashSet<String> dictionary = new HashSet<>();

        for (int i = 0; i < this.size(); i++) {
            Task task = this.getTaskAtIndex(i);
            String taskDescriptionWithoutSpace = task.getDescription().replaceAll("\\s", "");
            String descriptionWithoutSpace = description.replaceAll("\\s", "");
            if (description.equals(task.getDescription())) {
                dictionary.add(task.toString());
                sb.append((++numMatches) + ". " + task.toString() + "\n");
            } else if (taskDescriptionWithoutSpace.contains(descriptionWithoutSpace)) {
                dictionary.add(task.toString());
                sb.append((++numMatches) + ". " + task.toString() + "\n");
            }
        }
        if (numMatches == 0) {
            sb.append("\n Unfortunately, I could not find any exact matches.");
        }
        // Look for at least one similar match.
        Task taskSimilar = search.fuzzySearch(description, this, 5);
        if (taskSimilar != null && !dictionary.contains(taskSimilar.toString())) {
            sb.append("\n The following might be relevant to you: \n");
            sb.append((++numMatches) + ". " + taskSimilar.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Returns a string after deleteing task.
     *
     * @return  a String
     */
    public String deleteTaskAtIndexString(Integer i) throws IOException {
        if (i < 0 || i >= this.size()) {
            return "OOPS!!! The number to delete is invalid.";
        }
        Task toDelete = this.getTaskAtIndex(i);
        this.tasks.remove(toDelete);
        Storage.autoSave(this);
        return TaskList.announceRemovedString(toDelete);
    }

    /**
     * Announces that the task has been added.
     */
    public static void announceAdded(Task task) {
        System.out.println("Got it. I've added this task: \n");
        System.out.println(task.toString());
        System.out.println("Now we have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Similar to the announceAdded method.
     * @return a String instead of printing out.
     */
    public static String announceAddedString(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(task.toString() + "\n");
        sb.append("Now we have " + tasks.size() + " task(s) in the list.\n");
        return sb.toString();
    }

    /**
     * Announces that the task has been removed.
     *
     * @param task removed.
     */
    public static void announceRemoved(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString() + "\n");
        System.out.println("Now we have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Similar to the announceRemoved method.
     * @param task
     * @return a String instead of printing out.
     */
    public static String announceRemovedString(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(task.toString() + "\n");
        sb.append("Now we have " + tasks.size() + " task(s) in the list.\n");
        return sb.toString();
    }

    /**
     * Returns a string after marking a task.
     * @param i
     * @return a String
     */
    public String taskMarkedAtIndexString(Integer i) throws IOException {
        if (i < 0 || i >= this.size()) {
            return "OOPS!!! The number to mark is invalid.";
        }
        Task t = this.getTaskAtIndex(i);
        t.taskDone();
        Storage.autoSave(this);
        return t.taskDoneString();
    }

    /**
     * Returns a string after un-marking a task.
     * @param i
     * @return a String
     */
    public String taskUnmarkedAtIndexString(Integer i) throws IOException {
        if (i < 0 || i >= this.size()) {
            return "OOPS!!! The number to unmark is invalid.";
        }
        Task t = this.getTaskAtIndex(i);
        t.taskNotDone();
        Storage.autoSave(this);
        return t.taskNotDoneString();
    }

    /**
     * Prints all Task objects in the duke.task.TaskList in a readable manner.
     */
    public void printTaskList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = this.getTaskAtIndex(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the task(s) in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = this.getTaskAtIndex(i);
            sb.append((i + 1) + ". " + task.toString() + "\n");
        }
        return sb.toString();
    }

}

