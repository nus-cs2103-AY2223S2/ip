package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task list.
 */
public class Tasks {
    private List<Task> taskList;

    public Tasks() {
        this.taskList = new ArrayList<Task>(100);
    }

    /**
     * Prints the tasks in the task list.
     * @return The task list.
     */
    public String printList() {
        if (taskList.size() == 0) {
            return "NOTHIN; EMPTYYY?";
        }
        String output = "";
        int count = 1;
        for (Task task : taskList) {
            output += count + ". " + task.printTask() + "\n";
            count++;
        }
        return output;
    }
    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     * @param silent True if printing to user is not required, else false.
     * @return Response to user.
     */
    public String addToList(Task task, boolean silent) {
        taskList.add(task);
        String output = "";
        if (!silent) {
            output = "Added to list:\n" + task.printTask() + '\n';
            output += "Now you've got " + taskList.size() + " task(s) in your bag, CHOP CHOP GET THEM DONE.\n";
        }
        return output;
    }

    /**
     * Marks a task as done.
     * @param num The index of the task in the task list.
     * @param silent True if printing to user is not required, else false.
     * @return Response to user.
     */
    public String markTaskDone(int num, boolean silent) {
        if (withinRange(num)) {
            return this.taskList.get(num).markTaskDone(silent);
        }
        return "Hey HEY HEY, that's not within range";
    }

    /**
     * Marks a task undone.
     * @param num The index of the task in the task list.
     * @return Response to user.
     */
    public String markTaskUndone(int num) {
        if (withinRange(num)) {
            return this.taskList.get(num).markTaskUndone();
        }
        return "Hey HEY HEY, that's not within range";
    }

    /**
     * Deletes a task from the task list.
     * @param num The index of the task in the task list.
     * @return Response to user.
     */
    public String deleteTask(int num) {
        if (withinRange(num)) {
            String output = "Into the bin it goes! This is now deleted!\n" + this.taskList.get(num).printTask();
            this.taskList.remove(num);
            output += "\n" + this.taskList.size() + " task(s) left to go\n";
            return output;
        }
        return "Hey HEY HEY, that's not within range";
    }

    public boolean withinRange(int num) {
        return this.taskList.size() > num && num >= 0;
    }

    /**
     * Formats the tasks for storing to storage file.
     * @return A formatted string of all tasks.
     */
    public String formatForFile() {
        if (taskList.size() == 0) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        for (Task task : taskList) {
            output.append(task.formatForFile()).append("\n");
        }
        return output.toString();
    }

    /**
     * Filters tasks due or occurring on a given date.
     * @param dateOnly The given date.
     * @return Response to user.
     */
    public String filterByDate(String dateOnly) {
        if (taskList.size() == 0) {
            return "Nothing~";
        } else if (!MyDate.isValidDate(dateOnly)) {
            return "That's NOT a date";
        }
        String output = "";
        int count = 1;
        MyDate date = new MyDate(dateOnly);
        output += "Deadlines due or events ongoing on " + date.printDateTime() + "\n";
        for (Task task : taskList) {
            String result = printFilteredTask(task, date);
            if (!result.equals("")) {
                output += count + result;
                count++;
            }
        }
        return output;
    }

    /**
     * Checks if task is deadline or event that falls on given date.
     * @param task A task  in the task list.
     * @param date The date given by the user.
     * @return Empty string is task is not deadline or event, else prints the task.
     */
    public String printFilteredTask(Task task, MyDate date) {
        if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            if (d.isDeadLine(date)) {
                return ". " + task.printTask() + "\n";
            }
        }
        if (task instanceof Event) {
            Event e = (Event) task;
            if (e.liesBetween(date)) {
                return ". " + task.printTask() + "\n";
            }
        }
        return "";
    }

    /**
     * Filters and prints out tasks that contain a given keyword.
     * @param keyword The given keyword.
     * @return Response to user.
     */
    public String filterByKeyword(String keyword) {
        if (taskList.size() == 0) {
            return "Nothing~";
        }
        String output = "Tasks that have " + keyword + "\n";
        int count = 1;
        for (Task task : taskList) {
            if (task.containsKeyword(keyword)) {
                output += count + ". " + task.printTask() + "\n";
                count++;
            }
        }
        return output;
    }
}
