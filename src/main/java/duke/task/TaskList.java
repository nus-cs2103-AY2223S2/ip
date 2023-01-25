package task;
import exception.DukeException;
import storage.Storage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a TaskList
 */
public class TaskList {
    protected List<Task> tasks;
    public Storage storage;


    /**
     * Constructor to initialize a tasklist object
     */
    public TaskList() {
        storage = new Storage();
        List<Task> list = storage.loadData();
        this.tasks = list;

    }

    /**
     * Returns true if all tasks in the tasklist is completed; returns false otherwise.
     *
     */
    public boolean isAllCompleted() {
        for (int i = 0; i < tasks.size(); i++) {
            if (this.tasks.get(i).isMarked()) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the number of tasks in the tasklist.
     *
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     *  Returns the task in the ith position of the tasklist.
     *
     * @param i The index of the task in the tasklist
     * @return The task in the ith position
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Returns true if a task exists in the tasklist; returns false otherwise.
     *
     */
    public boolean doesTaskExist(int taskNum) {
        return taskNum > 0 && taskNum <= getNumTasks();
    }

    /**
     * Creates a new todo and adds it to the tasklist.
     *
     * @param desc The title of the todo task
     */
    public void addTodo(String desc) throws DukeException {
        Todo t = new Todo(desc);
        tasks.add(t);
        storage.save(this);
        System.out.println("    " + t);
        printNumTasks();
    }

    /**
     * Creates a new deadline and adds it to the tasklist.
     *
     * @param date The time/date of the deadline
     * @param desc The title of the deadline
     */

    public void addDeadline(LocalDate date, String desc) {
        Deadline d = new Deadline(date, desc);
        tasks.add(d);
        storage.save(this);
        System.out.println("    " + d);
        printNumTasks();
    }

    /**
     * Creates a new event and adds it to the tasklist.
     *
     * @param start The start date/time of the event
     * @param end The end date/time of the event
     * @param desc The title of the event
     */

    public void addEvent(LocalDate start, LocalDate end, String desc) {
        Event e = new Event(start, end, desc);
        tasks.add(e);
        storage.save(this);
        System.out.println("    " + e);
        printNumTasks();
    }

    /**
     * Deletes a task from the tasklist.
     *
     * @param taskNum The task number to be deleted
     */
    public void deleteTask(int taskNum) throws DukeException {
        System.out.println("    " + tasks.get(taskNum-1));
        tasks.remove(taskNum-1);
        storage.save(this);
        printNumTasks();
    }

    public void markTask(Task task) throws DukeException {
        if (task.isMarked) {
            throw new DukeException("This task has already been marked as done.");
        } else {
            task.mark();
            storage.save(this);
            System.out.println("Great job on completing this task! I've marked it as done:");
            System.out.println(task);
        }
        if (this.isAllCompleted()) {
            System.out.println("Yay! You have completed all your tasks :-)");
        }
    }

    public void unmarkTask(Task task) throws DukeException {
        if (!task.isMarked()) {
            throw new DukeException("Oops! This task has not been marked as done before.");
        } else {
            task.unMark();
            storage.save(this);
            System.out.println("Alright, I've marked this task as not done yet:");
            System.out.println(task);
        }
    }

    /**
     * Prints the number of tasks in the tasklist.
     */
    public void printNumTasks() {
        if (getNumTasks() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            String str = String.format("Now you have %d tasks in the list.", getNumTasks());
            System.out.println(str);
        }
    }


    /**
     * Returns the string representation of the tasklist.
     *
     * @return The string representation of the tasklist
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < getNumTasks(); i++) {
            if (i == getNumTasks()-1) {
                str += (i+1) + ". " + this.getTask(i);
            } else {
                str += (i+1) + ". " + this.getTask(i) + '\n';
            }
        }
        return str;
    }

}
