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
     * Returns the welcome message when user first run ShinChan.
     *
     * @return String displaying the welcome message whenever Shinchan is activated.
     */
    public static String showWelcome() {
        return "Hello from\n" + logo + "Konnichiwa! I'm Shinchan\nWhat can I do for you?";
    }

    public String showEditedMsg(Task task) {
        return showLine() + task + " has been edited!" + showLine();
    }

    /**
     * Returns the input typed by the user.
     *
     * @return String object containing the input by user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Returns the error message.
     *
     * @param errorMsg
     */
    public String showError(String errorMsg) {

        return showLine() + errorMsg + showLine();
    }

    /**
     * Returns all the commands available for the users to use and how to use it.
     * @return
     */
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

        return showLine() + availableHelp + showLine();
    }

    /**
     * Returns the message when the user type "list" command.
     */
    public String showListMessage() {
        return showLine() + "Here are the tasks in your list:";
    }

    /**
     * Returns the message when users call the command 'list' but the task list is empty
     * @return String object
     */
    public String showEmptyListMessage() {
        return showLine() + "No tasks created yet!!" + showLine();
    }

    /**
     * Returns the message when the users call a task number that is more than the total number of tasks in the list.
     * @param totalNumTasks
     * @return String object
     */
    public String showNonExistentTask(int totalNumTasks) {
        return showLine() + "You only have " + totalNumTasks + " tasks available..." + showLine();
    }

    /**
     * Returns the message when the users do not key in the keyword when they use the command 'find'
     * @return String object
     */
    public String showEmptyKeywordMsg() {
        return showLine() + "You have not key in any keyword :(" + showLine();
    }

    /**
     * Returns the message when shinchan cannot find the relevant tasks containing the keyword.
     * @return String object
     */
    public String showNoRelevantTasksMsg() {
        return showLine() + "No relevant task found oooopssss" + showLine();
    }

    /**
     * Returns the message when the users key in an invalid command.
     * @return String object
     */
    public String showInvalidCommandMsg() {
        return showLine() + "Invalid command! Use command 'help' to see the commands available for use :)" + showLine();
    }

    /**
     * Returns message when the user delete a task.
     *
     * @param task
     * @param tasksLen
     *
     * @return String object containing delete message displayed for user.
     */
    public String showDeleteMessage(Task task, String tasksLen) {
        return showLine() + "Action Kamen BEEEEPP BEEEPP BEEEP. I've removed this task:\n  "
                + task + "\nNow you have " + tasksLen + " tasks in the list." + showLine();
    }

    /**
     * Returns the message when the user add a task.
     *
     * @param task
     * @param tasksLen
     *
     * @return String object containing add task message displayed for user.
     */
    public String showAddTaskMsg(Task task, String tasksLen) {
        return showLine() + "Being an adult is tiring... I've added this task:\n  "
                + task + "\nNow you have " + tasksLen + " tasks in the list." + showLine();
    }

    /**
     * Returns the message when the user type "exit" command.
     *
     * @return String object containing exit message displayed for user.
     */
    public String exit() {
        return showLine() + "Bye. Hope to see you again soon!" + showLine();
    }

    /**
     * Returns the message when the user mark a task as done.
     *
     * @param task
     *
     * @return String object containing marked task message displayed for user.
     */
    public String showMarkedMsg(Task task) {
        return showLine() + "Action Kamen BEEEEPP BEEEPP BEEEP! This task is marked as done:\n" + task + showLine();
    }

    /**
     * Returns the message when the user mark a task as not done.
     *
     * @param task
     *
     * @return String object containing unmark task message displayed for user.
     */
    public String showUnmarkedMsg(Task task) {
        return showLine() + "Make up your mind!!! No chocobi for you before it is done:\n" + task + "\n"
                + "marked as incomplete." + showLine();
    }

    /**
     * Returns all the tasks containing the keyword requested by user.
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
        return showLine() + currStr + showLine();
    }

    /**
     * Returns the message when user type an unknown command.
     *
     * @return String object containing loading message displayed for user.
     */
    public String showLoadingError() {
        return showLine() + "No existing tasklist!" + showLine();
    }

    /**
     * Shows all the upcoming deadlines for the user when Shinchan is launched.
     * @param tasks
     * @return
     */
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

        return showLine() + "BEEEP POOO BEEP POOO ~~ You have " + tasks.size() + " upcoming tasks due\n"
                + str + showLine();
    }

    /**
     * Returns divider line.
     *
     * @return String object containing divider lines displayed for user.
     */
    public String showLine() {
        return "\n--------------------------------------------\n";
    }
}

