package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A list of tasks that a user can add, modify, view and delete.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> existingTasks) {
        this.tasks = existingTasks;
    }

    /**
     * Formats to string all the Tasks stored in TaskList.
     * @return String representation of all Tasks in TaskList.
     */
    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "List empty, add tasks!";
        } else {
            StringBuilder response = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                Task curTask = tasks.get(i);
                response.append((i+ 1)).append(".").append(curTask.toString());
                if (i < tasks.size() - 1) {
                    response.append("\n");
                }
            }
            return response.toString();
        }
    }

    public int size() {
        return tasks.size();
    }

    public String getTaskString(int index) {
        return tasks.get(index).toString();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Deletes a task from the TaskList based on its index.
     * @param index Index of task to be deleted.
     * @return Output string containing details of deleted Task.
     * @throws DukeException If index out of bounds.
     */
    public String deleteTask(int index) throws DukeException {
        assert tasks != null : "tasks array should be initialized";
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Error: Please input a valid task index!");
        }
        StringBuilder response = new StringBuilder();
        response.append("Noted. I've removed this task:\n");
        response.append("  ").append(this.getTaskString(index)).append("\n");
        tasks.remove(index);
        response.append("Now you have ").append(this.size()).append(" tasks in the list.");
        return response.toString();
    }

    /**
     * Marks a Task as completed.
     * @param index Index of task to mark as completed.
     * @return String containing details Task marked.
     * @throws DukeException If index out of bounds.
     */
    public String mark(int index) throws DukeException {
        assert tasks != null : "tasks array should be initialized";
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Task index out of bounds, please input a valid index");
        } else {
            Task curTask = tasks.get(index);
            curTask.setCompleted(true);
            String output;
            output = "Nice! I've marked this task as done:\n";
            return output + "  " + curTask.toString();
        }
    }

    /**
     * Marks a Task as not completed.
     * @param index Index of task to mark as not completed.
     * @return String containing details Task unmarked.
     * @throws DukeException If index out of bounds.
     */
    public String unmark(int index) throws DukeException {
        assert tasks != null : "tasks array should be initialized";
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Task index out of bounds, please input a valid index");
        } else {
            Task curTask = tasks.get(index);
            curTask.setCompleted(false);
            String output;
            output = "OK, I've marked this task as not done yet:\n";
            return output + "  " + curTask.toString();
        }
    }

    /**
     * Adds a Todo Task to the list.
     * @param description Description of Todo Task.
     * @return String with details of Todo Task added.
     */
    public String addTodo(String description) {
        assert tasks != null : "tasks array should be initialized";
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:\n");
        tasks.add(new Todo(description));
        response.append(getAfterAddStatus());
        return response.toString();
    }

    /**
     * Adds a Deadline Task to the list.
     * @param description Description of Deadline Task.
     * @param by Deadline date and time.
     * @return String with details of Deadline Task added.
     */
    public String addDeadline(String description, LocalDateTime by) {
        assert tasks != null : "tasks array should be initialized";
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:\n");
        tasks.add(new Deadline(description, by));
        response.append(getAfterAddStatus());
        return response.toString();
    }

    /**
     * Adds an Event Task to the list
     * @param description Description of Event Task.
     * @param from Start date and time of Event.
     * @param to End date and time of Event.
     * @return String with details of Event added.
     */
    public String addEvent(String description, LocalDateTime from, LocalDateTime to) {
        assert tasks != null : "tasks array should be initialized";
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:\n");
        tasks.add(new Event(description, from, to));
        response.append(getAfterAddStatus());
        return response.toString();
    }

    /**
     * Generates string with number of Tasks in the list and added Task details.
     * @return String with number of Task and details of added Task.
     */
    private String getAfterAddStatus() {
        int count = tasks.size();
        StringBuilder response = new StringBuilder();
        response.append("  ").append(getTaskString(count - 1)).append("\n");
        response.append("Now you have ").append(count).append(" tasks in the list.");
        return response.toString();
    }

    public String find(String keyword) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list\n");
        int taskCount = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.getDescription().contains(keyword)) {
                response.append(String.format("%d. %s\n", taskCount, currentTask.toString()));
                taskCount++;
            }
        }
        if (taskCount == 1) {
            response.append("No task matching description");
        }
        return response.toString();
    }
}
