package storage;

import task.Task;
import task.Event;
import task.Deadline;
import task.ToDo;
import exception.DukeException;

import java.util.ArrayList;

/**
 * Represents list of task. A <code>TaskList</code> object corresponds to
 * the list of tasks that the user has recorded
 */
public class TaskList {
    private final ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Prints tasks in the task list.
     *
     */
    public void list() {
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ": " + list.get(i - 1));
        }
    }
    /**
     * Adds a task to the task list.
     *
     * @param newTask Task to be added.
     */
    public void add(Task newTask) {
        list.add(newTask);

    }

    /**
     * Converts a string into a task and adds it to the task list.
     *
     * @param input String to be converted to task, then added to task list.
     */
    public void add(String input) {
        Task newTask;
        String[] inputs = input.split(" ");
        String type = inputs[0];
        switch(type) {
            case "todo": {
                if (inputs.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                String name = input.split(" ", 2)[1];
                newTask = new ToDo(name);
                break;
            }
            case "deadline": {
                if (inputs.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] nameAndDeadline = input.split(" ", 2)[1].split(" /by ");
                if (nameAndDeadline.length < 2) {
                    throw new DukeException("☹ OOPS!!! The deadline of a deadline cannot be empty.");
                }
                String name = nameAndDeadline[0];
                String[] deadline = nameAndDeadline[1].split(" ");
                String[] date = deadline[0].split("/");
                String remarks = " | " + nameAndDeadline[1];
                for (int i = 0; i < date.length; i++) {
                    if (date[i].length() < 2) {
                        date[i] = "0" + date[i];
                    }
                }
                String newDate = date[2] + "-" + date[1] + "-" + date[0];
                if (deadline.length == 1) {
                    newTask = new Deadline(name, newDate, remarks);
                } else {
                    String[] time = deadline[1].split("");
                    String newTime = time[0] + time[1] + ":" + time[2] + time[3];
                    newTask = new Deadline(name, newDate, newTime, remarks);
                }
                break;
            }
            case "event": {
                if (inputs.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                String[] nameAndStart = input.split(" ", 2)[1].split(" /from ");
                if (nameAndStart.length < 2) {
                    throw new DukeException("☹ OOPS!!! The start of a event cannot be empty.");
                }
                String name = nameAndStart[0];
                String[] startAndEnd = nameAndStart[1].split(" /to ");
                if (startAndEnd.length < 2) {
                    throw new DukeException("☹ OOPS!!! The end of a event cannot be empty.");
                }
                String[] start = startAndEnd[0].split(" ");
                String[] startDate = start[0].split("/");
                String[] end = startAndEnd[1].split(" ");
                String[] endDate = end[0].split("/");
                for (int i = 0; i < startDate.length; i++) {
                    if (startDate[i].length() < 2) {
                        startDate[i] = "0" + startDate[i];
                    }
                    if (endDate[i].length() < 2) {
                        endDate[i] = "0" + endDate[i];
                    }
                }
                String newStartDate = startDate[2] + "-" + startDate[1] + "-" + startDate[0];
                String newEndDate = endDate[2] + "-" + endDate[1] + "-" + endDate[0];
                String remarks = " | " + startAndEnd[0] + " | " + startAndEnd[1];
                if (start.length > 1) {
                    String[] startTime = start[1].split("");
                    String newStartTime = startTime[0] + startTime[1] + ":" + startTime[2] + startTime[3];
                    String[] endTime = end[1].split("");
                    String newEndTime = endTime[0] + endTime[1] + ":" + endTime[2] + endTime[3];
                    newTask = new Event(name, newStartDate, newEndDate, newStartTime, newEndTime, remarks);
                } else {
                    newTask = new Event(name, newStartDate, newEndDate, remarks);
                }
                break;
            }
            default: {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        list.add(newTask);
    }
    /**
     * Deletes the task from task list.
     *
     * @param num Index of the task to be deleted.
     */
    public void delete(int num) {
        Task removed = list.remove(num - 1);
    }

    /**
     * Marks the task in task list as done.
     *
     * @param num Index of the task to be marked as done.
     */
    public void mark(int num) {
        list.get(num - 1).mark();
    }

    /**
     * Marks the task in task list as undone.
     *
     * @param num Index of the task to be marked as undone.
     */
    public void unmark(int num) {
        list.get(num - 1).unmark();
    }

    /**
     * Returns the number of tasks in task list.
     *
     * @return Size of task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the task with the specified index in task list.
     *
     * @param num Index of the task to be returned.
     * @return Task at index num.
     */
    public Task get(int num) {
        return list.get(num - 1);
    }

    /**
     * Returns the last task in task list.
     *
     * @return Task at end of task list.
     */
    public Task getLast() {
        return list.get(list.size() - 1);
    }
}