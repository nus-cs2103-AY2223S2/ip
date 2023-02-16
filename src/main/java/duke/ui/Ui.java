package duke.ui;

import java.util.ArrayList;

import duke.TaskList;
import duke.tasks.Task;


public class Ui {


    /**
     * Adds a task to the task list
     *
     * @param task    The task created
     * @param counter The counter of the number of total tasks
     */
    public String addTask(Task task, int counter) {
        return ("Got it. I've added this task:\n"
            + "  " + task.toString() + "\n"
            + "Now you have " + counter + " tasks in the list.");
    }

    /**
     * Displays all the current tasks
     *
     * @param list The task list
     */
    public String listUI(TaskList list) {
        String tasks = "";
        for (int j = 0; j < list.getLength(); j++) {
            int k = j + 1;
            tasks = tasks.concat(k + "." + list.getTask(j).toString() + "\n");
        }
        return ("Here are the tasks in your list:\n"
            + tasks);
    }

    /**
     * Marks a task as finished and displays to the user
     *
     * @param task The task finished
     */
    public String mark(Task task) {
        return (
            "Nice! I've marked this task as done:\n"
                + "  " + task.toString());
    }


    /**
     * Displays that the file is not found
     */
    public static String fileExceptionUi() {
        return ("   OOPS!!! File not found, please create the file \"duke.txt\" first\n");

    }

    /**
     * Shows error given as the input
     *
     * @param error Error input
     */
    public static String showError(String error) {
        if (!error.equals("wrong")) {
            return (error);

        } else {
            return ("   OOPS!!! I'm sorry, but I don't know what that means :-(\n");

        }
    }


    /**
     * Marks the task as unfinished
     *
     * @param task The task to be marked
     */
    public String unmark(Task task) {
        return ("OK, I've marked this task as not done yet:\n"
            + "  " + task.toString());
    }


    /**
     * Deletes a task from the task list
     *
     * @param task    The task to be deleted
     * @param counter The counter of the total number of tasks
     */
    public static String delete(Task task, int counter) {
        return ("Noted. I've removed this task:\n"
            + "  " + task.toString() + "\n"
            + "Now you have " + counter + " tasks in the list.");

    }

    /**
     * Tells the user that no command has been added
     *
     * @return String meaning no command added yet
     */
    public static String cannotFindPreCommand() {
        return ("Sorry there is no command yet.");
    }

    /**
     * Tells the user that previous command is not able to be undone
     *
     * @return String meaning previous command cannot be undone
     */
    public static String failUndo() {
        return ("Sorry the previous command cannot be undone.");
    }


    /**
     * Finds tasks in the task list
     *
     * @param found The indexes of the tasks found in the task list
     * @param list  The task list
     */
    public static String find(ArrayList<Integer> found, TaskList list) {
        int counter = 1;
        String tasks = "";
        for (Integer cur : found) {
            tasks = tasks.concat(counter + "." + list.getTask(cur).toString() + "\n");
            counter += 1;
        }
        return ("Here are the matching tasks in your list:\n" +
            tasks);


    }


}
