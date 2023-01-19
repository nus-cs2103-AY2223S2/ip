package task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a TaskList
 */
public class TaskList {
    protected List<Task> tasks;


    /**
     * Constructor to initialize a tasklist object
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
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
    public void addTodo(String desc) {
        Todo t = new Todo(desc);
        tasks.add(t);
        System.out.println("    " + t);
        printNumTasks();
    }

    /**
     * Creates a new deadline and adds it to the tasklist.
     *
     * @param date The time/date of the deadline
     * @param desc The title of the deadline
     */
    public void addDeadline(String date, String desc) {
        Deadline d = new Deadline(date, desc);
        tasks.add(d);
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
    public void addEvent(String start, String end, String desc) {
        Event e = new Event(start, end, desc);
        tasks.add(e);
        System.out.println("    " + e);
        printNumTasks();
    }

    /**
     * Deletes a task from the tasklist.
     *
     * @param taskNum The task number to be deleted
     */
    public void deleteTask(int taskNum) {
        System.out.println("    " + tasks.get(taskNum-1));
        tasks.remove(taskNum-1);
        printNumTasks();
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
