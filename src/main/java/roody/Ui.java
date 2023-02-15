package roody;

import java.util.ArrayList;

import roody.exceptions.RoodyException;
import roody.tasks.Task;

/**
 * Represents a User Interface.
 */
public class Ui {


    /**
     * Creates a User Interface.
     */
    public Ui() {}

    /**
     * Returns basic line.
     * @return A Line.
     */
    public String showLine() {
        return "____________________________________________________________\n";
    }

    /**
     * Returns start of line
     * @return An arrow for start of line.
     */
    public String startNextLine() {
        return "=> ";
    }

    /**
     * Returns a successful task addition message.
     * @param task  Task that has been added.
     * @param listLength Length of the current list after addition.
     * @return Delete task message.
     */
    public String showAddTask(Task task, int listLength) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Got it. I've added this task:\n");
        stringBuilder.append(task.toString() + '\n');
        stringBuilder.append("Now you have " + listLength + " tasks in the list.\n");
        return stringBuilder.toString();
    }

    /**
     * Returns a successful task deletion message.
     * @param task  Task that has been added.
     * @param listLength Length of the current list after deletion.
     * @return Delete task message.
     */
    public String showDeleteTask(Task task, int listLength) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Noted. I've removed this task:\n");
        stringBuilder.append(task.toString() + '\n');
        stringBuilder.append("Now you have " + listLength + " tasks in the list.\n");
        return stringBuilder.toString();
    }

    /**
     * Returns a successful marking of task message.
     * @param task  Task that has been added.
     * @return Marking message.
     */
    public String showMarkStatus(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nice! I've marked this task as done:\n");
        stringBuilder.append(task.toString() + '\n');
        return stringBuilder.toString();
    }

    /**
     * Returns a successful unmarking of task message..
     * @param task  Task that has been added.
     * @return Marking message.
     */
    public String showUnmarkStatus(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OK, I've marked this task as not done yet:\n");
        stringBuilder.append(task.toString() + '\n');
        return stringBuilder.toString();
    }

    /**
     * Shows tasks that are found with relevant keywords in a String.
     * @param list List of found tasks.
     * @return Tasks with keywords in List.
     * @throws RoodyException If no matching tasks found.
     */
    public String showFoundTasks(ArrayList<Task> list) throws RoodyException {
        if (list.size() == 0) {
            throw new RoodyException("No matching tasks in your list!");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:\n");
        Integer listIndex = 0;
        for (Task task : list) {
            listIndex++;
            stringBuilder.append(listIndex.toString() + '.' + task.toString() + '\n');
        }
        assert list.size() == listIndex : "Error while printing, task number mismatch";
        return stringBuilder.toString();
    }


    /**
     * Returns tasks in given list as a String.
     * @param list List of tasks to be printed.
     * @return Tasks in List.
     * @throws RoodyException If not tasks in list.
     */
    public String printList(ArrayList<Task> list) throws RoodyException {
        if (list.size() == 0) {
            throw new RoodyException("There doesn't seem to be any tasks in your list.");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        Integer listIndex = 0;
        for (Task task : list) {
            listIndex++;
            stringBuilder.append(listIndex.toString() + ". " + task.toString() + '\n');
        }
        assert list.size() == listIndex : "Error while printing, task number mismatch";
        return stringBuilder.toString();
    }

    /**
     * Returns basic greeting string.
     */
    public String greet() {
        return "Hello, I'm Roody!\nWhat can I do for you?\n";
    }

    /**
     * Prints basic farewell string.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!\n";
    }
}
