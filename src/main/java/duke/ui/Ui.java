package duke.ui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Task;

/**
 * Class for the Ui object.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */

public class Ui {
    private static String logo =
            "      _     _            _                 \n"
                    + "     | |   (_)          | |                \n"
                    + "  ___| |__  _ _ __   ___| |__   __ _ _ __  \n"
                    + " / __| '_ \\| | '_ \\ / __| '_ \\ / _` | '_ \\ \n"
                    + " \\__ \\ | | | | | | | (__| | | | (_| | | | |\n"
                    + " |___/_| |_|_|_| |_|\\___|_| |_|\\__,_|_| |_|\n";
    private final Scanner sc;

    /**
     * Constructor for Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Print the welcome message when user first run ShinChan.
     *
     * @return String displaying the welcome message whenever Shinchan is activated.
     */
    public static String showWelcome() {
        return "Hello from\n" + logo + "Konnichiwa! I'm Shinchan\nWhat can I do for you?";
    }

    public String showEditedMsg(Task task) {
        return task + " has been edited!";
    }

    /**
     * Get the input typed by the user.
     *
     * @return String object containing the input by user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Show the error message.
     *
     * @param errorMsg
     */
    public String showError(String errorMsg) {
        return errorMsg;
    }

    public String showHelp() {
        String availableHelp = "These are the commands I recognise given my age:\n"
                + "- todo [task description]: add a task of type Todo\n"
                + "- deadline [task description] /by [due date]: add a task of type Deadline, "
                + "shinchan only can comprehend date of format dd-mm-yyyy HHmm :(\n"
                + "- event [task description] /from [from when] /by [by when]: add a task of type Event\n"
                + "- delete [task number to be deleted]: to delete a task\n"
                + "- find [keyword]: to find a task that contain certain work\n"
                + "- list: to list out all your tasks\n"
                + "- mark [task number you wish to mark]: mark a task completed\n"
                + "- unmark [task number you wish to unmark]: mark a task as incompleted\n"
                + "- edit /[task number u want to edit] /[desc/from/to/by] /[content you want change to]: edit task\n"
                + "- bye: shinchan pangkang!\n";

        return availableHelp;
    }

    /**
     * Print the message when the user type "list" command.
     */
    public String showListMessage() {
        return "Here are the tasks in your list:";
    }

    /**
     * Print message when the user delete a task.
     *
     * @param task
     * @param tasksLen
     *
     * @return String object containing delete message displayed for user.
     */
    public String showDeleteMessage(Task task, String tasksLen) {
        return "Action Kamen BEEEEPP BEEEPP BEEEP. I've removed this task:\n  "
                + task + "\nNow you have " + tasksLen + " tasks in the list.";
    }

    /**
     * Print the message when the user add a task.
     *
     * @param task
     * @param tasksLen
     *
     * @return String object containing add task message displayed for user.
     */
    public String showAddTaskMsg(Task task, String tasksLen) {
        return "Being an adult is tiring... I've added this task:\n  "
                + task + "\nNow you have " + tasksLen + " tasks in the list.";
    }

    /**
     * Print the message when the user type "exit" command.
     *
     * @return String object containing exit message displayed for user.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Print the message when the user mark a task as done.
     *
     * @param task
     *
     * @return String object containing marked task message displayed for user.
     */
    public String showMarkedMsg(Task task) {
        return "Action Kamen BEEEEPP BEEEPP BEEEP! This task is marked as done:\n" + task;
    }

    /**
     * Print the message when the user mark a task as not done.
     *
     * @param task
     *
     * @return String object containing unmark task message displayed for user.
     */
    public String showUnmarkedMsg(Task task) {
        return "Make up your mind!!! No chocobi for you before it is done:\n" + task + "\n" + "marked as incomplete.";
    }

    /**
     * Print all the tasks containing the keyword requested by user.
     *
     * @param tasklist
     *
     * @return String object containing tasks with the keyword specified by users displayed for user.
     */
    public String showRelevantTasks(ArrayList<Task> tasklist) {
        String currStr = "That was a hard word... but here's the matching tasks in your list:\n";
        int counter = 1;
        for (int i = 0; i < tasklist.size(); i++) {
            currStr = currStr + counter + "." + tasklist.get(i);
        }
        return currStr;
    }

    /**
     * Print the message when user type an unknown command.
     *
     * @return String object containing loading message displayed for user.
     */
    public String showLoadingError() {
        return "No existing tasklist!";
    }

    public String showReminders () {
        return "BEEEP POOO BEEP POOO ~~ You have an upcoming deadline";
    }

    public String showUpcomingTasks(ArrayList<Deadline> tasks) {
        String str = "";

        if (tasks.size() == 0) {
            return showLine() + "No upcoming tasks due~~" + showLine();
        } else {
            int counter = 1;
            for (int i = 0; i < tasks.size(); i++) {
                Deadline currTask = tasks.get(i);
                str = str + counter + ". " + currTask.getDescription() + "due: "
                        + currTask.getDeadline().format(DateTimeFormatter.ofPattern("MMM dd yyyy hhmm a")) + "\n";
                counter++;
            }
        }

        return showLine() + "You have " + tasks.size() + " upcoming tasks due\n" + str;
    }

    /**
     * Print divider line.
     *
     * @return String object containing divider lines displayed for user.
     */
    public String showLine() {
        return "\n---------------------------------\n";
    }
}

