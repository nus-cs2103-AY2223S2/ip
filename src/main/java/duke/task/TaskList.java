package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

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
     * @param t the Task object to be added.
     */
    public static void addTask(Task t) {
        tasks.add(t);
        TaskList.announceAdded();
    }

    /**
     * Returns a Task object at a specific index.
     *
     * @param i index.
     * @return Task object at index.
     */
    public Task getTaskAtIndex(Integer i) {
        return tasks.get(i);
    }

    /**
     * Returns a duke.task.TaskList of tasks matching the string description.
     *
     * @param description of a task.
     */
    public void matchDescription(String description) {
        FuzzySearch search = new FuzzySearch();
        System.out.println("Here are the tasks matched in your list: ");
        Integer numMatches = 0;
        Boolean noExactMatch = true;
        // Look for exact matches first.
        for (int i = 0; i < tasks.size(); i++) {
            Task task = this.getTaskAtIndex(i);
            if (task.getDescription().equals(description)) {
                noExactMatch = false;
                System.out.println((++numMatches) + ". " + task.toString());
            }
        }
        // Look for similar matches.
        Task similarTask = search.fuzzySearch(description, this, 5);
        if (noExactMatch && similarTask != null) {
            System.out.println("It seems that there are no exact matches. \n "
                    + "Here's the most similar task I could find");
            System.out.println(1 + ". " + similarTask.toString());
        } else if (noExactMatch && numMatches == 0) {
            System.out.println("It seems that there are no matches.");
        }
    }

    /**
     * Identical to matchDescription() but returns string instead.
     * @param description
     * @return a String
     */
    public String matchDescriptionString(String description) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks matched in your list: \n");
        FuzzySearch search = new FuzzySearch();
        Integer numMatches = 0;
        Boolean noExactMatch = true;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = this.getTaskAtIndex(i);
            if (task.getDescription().equals(description)) {
                sb.append((++numMatches) + ". " + task.toString() + "\n");
            }
        }
        // Look for similar matches.
        Task similarTask = search.fuzzySearch(description, this, 5);
        if (noExactMatch && similarTask != null) {
            sb.append("It seems that there are no exact matches. \n "
                    + "Here's the most similar task I could find \n");
            sb.append(1 + ". " + similarTask.toString());
        }
        return sb.toString();
    }


    /**
     * Attempts to delete a Task object in the duke.task.TaskList at a specified index if it exists.
     *
     * @param i index.
     */
    public void deleteTaskAtIndex(Integer i) {
        try {
            if (i < 0 || i >= tasks.size()) {
                throw new DukeException("☹ OOPS!!! The number to delete is invalid.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        Task toDelete = this.getTaskAtIndex(i);
        tasks.remove(toDelete);
        TaskList.announceRemoved(toDelete);
    }

    /**
     * Identical to deleteTaskAtIndex() but returns a String.
     *
     * @return  a String
     */
    public String deleteTaskAtIndexString(Integer i) {
        if (i < 0 || i >= tasks.size()) {
            return "OOPS!!! The number to delete is invalid.";
        }
        Task toDelete = this.getTaskAtIndex(i);
        tasks.remove(toDelete);
        return TaskList.announceRemovedString(toDelete);
    }

    /**
     * Announces that the task has been added.
     */
    public static void announceAdded() {
        System.out.println("Got it. I've added this task:");
        System.out.println("Now we have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Similar to the announceAdded method.
     * @return a String instead of printing out.
     */
    public static String announceAddedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append("Now we have " + tasks.size() + " task(s) in the list.\n");
        return sb.toString();
    }

    /**
     * Announces that the task has been removed.
     *
     * @param t task removed.
     */
    public static void announceRemoved(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.toString());
        System.out.println("Now we have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Similar to the announceRemoved method.
     * @param t
     * @return a String instead of printing out.
     */
    public static String announceRemovedString(Task t) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(t.toString() + "\n");
        sb.append("Now we have " + tasks.size() + " tasks in the list.\n");
        return sb.toString();
    }

    /**
     * Attempts to mark a Task in duke.task.TaskList as completed if it exists.
     *
     * @param i index.
     */
    public void taskMarkedAtIndex(Integer i) {
        try {
            if (i < 0 || i >= this.size()) {
                throw new DukeException("☹ OOPS!!! The number to mark is invalid.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        this.getTaskAtIndex(i).taskDone();
    }

    /**
     * Identical to taskMarkedAtIndex() but returns a String.
     * @param i
     * @return a String
     */
    public String taskMarkedAtIndexString(Integer i) {
        if (i < 0 || i >= this.size()) {
            return "OOPS!!! The number to mark is invalid.";
        }
        return this.getTaskAtIndex(i).taskDoneString();
    }

    /**
     * Attempts to mark a Task in duke.task.TaskList as incomplete if it exists.
     *
     * @param i index.
     */
    public void taskUnmarkedAtIndex(Integer i) {
        try {
            if (i < 0 || i >= this.size()) {
                throw new DukeException("OOPS!!! The number to unmark is invalid.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        this.getTaskAtIndex(i).taskNotDoneString();
    }

    /**
     * Identical to taskUnmarkedAtIndex() but returns a String.
     * @param i
     * @return a String
     */
    public String taskUnmarkedAtIndexString(Integer i) {
        if (i < 0 || i >= this.size()) {
            return "OOPS!!! The number to unmark is invalid.";
        }
        return this.getTaskAtIndex(i).taskNotDoneString();
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
        sb.append("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = this.getTaskAtIndex(i);
            sb.append((i + 1) + ". " + task.toString() + "\n");
        }
        return sb.toString();
    }

}

