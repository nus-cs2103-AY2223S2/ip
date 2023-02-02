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
        System.out.println("Here are the tasks matched in your list: ");
        Integer j = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = this.getTaskAtIndex(i);
            if (task.getDescription().equals(description)) {
                System.out.println((++j) + ". " + task.toString());
            }
        }
        if (j == 0) {
            System.out.println("It seems that there are no matches.");
        }
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
     * Announces that the task has been added.
     */
    public static void announceAdded() {
        System.out.println("Got it. I've added this task:");
        System.out.println("Now we have " + tasks.size() + " tasks in the list.");
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
     * Attempts to mark a Task in duke.task.TaskList as incomplete if it exists.
     *
     * @param i index.
     */
    public void taskUnmarkedAtIndex(Integer i) {
        try {
            if (i < 0 || i >= this.size()) {
                throw new DukeException("☹ OOPS!!! The number to mark is invalid.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        this.getTaskAtIndex(i).taskNotDone();
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
}

