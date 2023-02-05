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
        } else {
            String output = "";
            int count = 1;
            for (Task task : taskList) {
                output += count + ". " + task.printTask() + "\n";
                count++;
            }
            return output;
        }
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
        String output = "";
        if (withinRange(num)) {
            output += this.taskList.get(num).markTaskDone(silent);
        } else {
            output += "Hey HEY HEY, that's not within range";
        }
        return output;
    }

    /**
     * Marks a task undone.
     * @param num The index of the task in the task list.
     * @return Response to user.
     */
    public String markTaskUndone(int num) {
        String output = "";
        if (withinRange(num)) {
            output += this.taskList.get(num).markTaskUndone();
        } else {
            output += "Hey HEY HEY, that's not within range";
        }
        return output;
    }

    /**
     * Deletes a task from the task list.
     * @param num The index of the task in the task list.
     * @return Response to user.
     */
    public String deleteTask(int num) {
        String output;
        if (withinRange(num)) {
            output = "Into the bin it goes! This is now deleted!\n" + this.taskList.get(num).printTask();
            this.taskList.remove(num);
            output += "\n" + this.taskList.size() + " task(s) left to go\n";
        } else {
            output = "Hey HEY HEY, that's not within range";
        }
        return output;
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
        } else {
            StringBuilder output = new StringBuilder();
            for (Task task : taskList) {
                output.append(task.formatForFile()).append("\n");
            }
            return output.toString();
        }
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
        } else {
            String output = "";
            int count = 1;
            MyDate date = new MyDate(dateOnly);
            output += "Deadlines due or events ongoing on " + date.printDateTime() + "\n";
            for (Task task : taskList) {
                if (task instanceof Deadline) {
                    Deadline d = (Deadline) task;
                    if (d.isDeadLine(date)) {
                        output += count + ". " + task.printTask() + "\n";
                        count++;
                    }
                } else if (task instanceof Event) {
                    Event e = (Event) task;
                    if (e.liesBetween(date)) {
                        output += count + ". " + task.printTask() + "\n";
                        count++;
                    }
                }
            }
            return output;
        }
    }

    /**
     * Filters and prints out tasks that contain a given keyword.
     * @param keyword The given keyword.
     * @return Response to user.
     */
    public String filterByKeyword(String keyword) {
        if (taskList.size() == 0) {
            return "Nothing~";
        } else {
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
}
