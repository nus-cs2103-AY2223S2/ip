package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents the entire list of Tasks.
 */
public class TaskList {
    protected List<Task> tasks;

    /**
     * A constructor to initialize a TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns true if all tasks in this TaskList are completed; returns false otherwise.
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


    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Returns the task in the i-th position of this TaskList.
     *
     * @param i The index of the task in this TaskList.
     * @return The task in the i-th position of this TaskList.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Returns true if a task exists in this TaskList; returns false otherwise.
     */
    public boolean doesTaskExist(int taskNum) {
        return taskNum > 0 && taskNum <= getNumTasks();
    }

    /**
     * Creates a new Todo task and adds it to this TaskList.
     *
     * @param desc The description of the Todo task to add to this TaskList.
     */
    public void addTodo(String desc) {
        Todo t = new Todo(desc);
        tasks.add(t);
        System.out.println("    " + t);
        printNumTasks();
    }

    /**
     * Creates a new Deadline task and adds it to this TaskList.
     *
     * @param date The date of the Deadline task.
     * @param desc The description of the Deadline task.
     */

    public void addDeadline(LocalDate date, String desc) {
        Deadline d = new Deadline(date, desc);
        tasks.add(d);
        System.out.println("    " + d);
        printNumTasks();
    }

    /**
     * Creates a new Event task and adds it to this TaskList.
     *
     * @param start The start date of the Event task.
     * @param end The end date/time of the Event task.
     * @param desc The description of the Event task.
     */

    public void addEvent(LocalDate start, LocalDate end, String desc) {
        Event e = new Event(start, end, desc);
        tasks.add(e);
        System.out.println("    " + e);
        printNumTasks();
    }

    /**
     * Deletes a task from this TaskList.
     *
     * @param taskNum The number of the task to be deleted.
     */
    public void deleteTask(int taskNum) throws DukeException {
        System.out.println("    " + tasks.get(taskNum - 1));
        tasks.remove(taskNum - 1);
        printNumTasks();
    }

    /**
     * Marks a task in this TaskList.
     *
     * @param task The task to be marked.
     * @throws DukeException If the task has already been marked.
     */
    public void markTask(Task task) throws DukeException {
        if (task.isMarked) {
            throw new DukeException("This task has already been marked as done.");
        } else {
            task.mark();
            System.out.println("Great job on completing this task! I've marked it as done:");
            System.out.println(task);
        }
        if (this.isAllCompleted()) {
            System.out.println("Yay! You have completed all your tasks :-)");
        }
    }

    /**
     * Unmarks a task in this TaskList.
     *
     * @param task The task to be marked.
     * @throws DukeException If the task is already unmarked.
     */
    public void unmarkTask(Task task) throws DukeException {
        if (!task.isMarked()) {
            throw new DukeException("Oops! This task has not been marked as done before.");
        } else {
            task.unMark();
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

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < getNumTasks(); i++) {
            if (i == getNumTasks() - 1) {
                str += (i + 1) + ". " + this.getTask(i);
            } else {
                str += (i + 1) + ". " + this.getTask(i) + '\n';
            }
        }
        return str;
    }

}
