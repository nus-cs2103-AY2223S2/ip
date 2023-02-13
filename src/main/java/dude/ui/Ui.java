package dude.ui;

import java.util.List;
import java.util.Scanner;

import dude.task.Task;
import dude.task.TaskList;

/**
 * Handles interactions with user.
 */
public class Ui {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Prints horizontal line format for program.
     */
    public void showLine() {
        System.out.println(" _______________________________________________________________________");
    }

    /**
     * Prints welcome message to user.
     */
    public String showWelcome() {
        return "Eh hello! I'm dude. What you want me do for you?\n";
    }

    /**
     * Prints list of task in TaskList for user.
     *
     * @param tasks TaskList to be printed to user.
     */
    public String showList(TaskList tasks) {
        return tasks.toString();
    }

    /**
     * Prints add message and added task to user.
     *
     * @param newTask Task added to be printed to user.
     */
    public String showAdd(Task newTask) {
        return "Got it. I've added this task already:\n" + "" + newTask + "\n"
                + "Now got " + Task.getTaskCount() + " tasks in your list liao.";
    }

    /**
     * Prints delete message and deleted task to user.
     *
     * @param currentTask Task to be printed to user.
     */
    public String showDelete(Task currentTask) {
        return "Okay can. I've removed this task already:\n" + "" + currentTask + "\n"
                + "Now only left with " + Task.getTaskCount() + " tasks in your list liao.";
    }

    /**
     * Prints mark message and marked task to user.
     *
     * @param currentTask Task to be printed to user.
     */
    public String showMark(Task currentTask) {
        return "Swee! I've marked this task as done loh:\n" + "" + currentTask + "\n";
    }

    /**
     * Prints unmark message and unmarked task to user.
     *
     * @param currentTask Task to be printed to user.
     */
    public String showUnmark(Task currentTask) {
        return "Okay liar, I've marked this task as undone liao:\n" + "" + currentTask + "\n";
    }

    /**
     * Searches the list of task containing a particular keyword
     *
     * @param tasks List of Task for searching
     */
    public String showFind(List<Task> tasks) {
        StringBuilder result = new StringBuilder();
        assert tasks.size() > 0 : "Number of tasks should be more than 0";
        if (tasks.size() != 0) {
            result.append("Okay come, here are the task I found containing your keyword: ");
            for (int i = 0; i < tasks.size(); i++) {
                result.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
            }
            return result.toString();
        } else {
            return "I cannot find any task containing your keyword sia\n";
        }
    }

    /**
     * Prints success undo message to user.
     */
    public String showUndo() {
        return "Okay la. Your previous command has been undone\n";
    }

    /**
     * Prints failed undo message to user.
     */
    public String showUndoError() {
        return "Sorry leh. I think your previous command cannot be undone\n";
    }

    /**
     * Prints error message to user.
     *
     * @param error Error message to be printed.
     */
    public String showError(String error) {
        return error + "\n";
    }

    /**
     * Prints goodbye message to user.
     */
    public String showGoodbye() {
        return "Ciaos! See you next time\n";
    }

    /**
     * Prints loading error message to user.
     */
    public String showLoadingError() {
        return "Bzzz.. Sorry ah, I think something went wrong with me, am unable to wake up\n";
    }

    /**
     * Reads user command input and filters empty line.
     */
    public String readCommand() {
        return sc.nextLine();
    }

}
