package dukes.util;

import dukes.task.Task;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The util class for reading inputs and providing feedbacks to user.
 */
public class UI {
    // mainly use static methods
    // need to handle the exceptions raised by Parser
    public static final String WORD_DIVISHION_LINE = "________________________________________";
    public static final String WORD_LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String WORD_GREET = "It's a pleasure to serve you!";
    public static final String WORD_BYE = "Goodbye. Hope you have a nice day!";
    public static final String WORD_MARK_DONE =
            "Nice! You have completed this task: ";
    public static final String WORD_MARK_UNDONE =
            "Well, you have not finished this task yet: ";
    public static final String WORD_FIND = "Here are the matching tasks in list:  ";
    public static final String WORD_LIST = "Here are all of your tasks: ";
    public static final String WORD_SEARCH = "Here are your tasks on the given date: ";
    public static final String WORD_ADD = "This task is added to your list: ";
    public static final String WORD_DELETE = "Ok, I will remove this task for you: ";

    /**
     * Read in user command inputs.
     *
     * @param sc the scanner to read inputs.
     * @return a string read from the input.
     */
    public static String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Display welcome message when Duke starts.
     */
    public static void showWelcome() {
        showLine();
        System.out.println(WORD_LOGO);
        System.out.println(WORD_GREET);
        showLine();
    }

    /**
     * Display error message when data file is not found in the hard disk.
     *
     * @param msg error message to be displayed.
     */
    public static void showLoadingError(String msg) {
        System.out.println(msg);
    }

    /**
     * Display the decorative horizontal bar.
     */
    public static void showLine() {
        System.out.println(WORD_DIVISHION_LINE);
    }

    /**
     * Display error message when runtime issue happens for Duke.
     *
     * @param msg error message to be displayed.
     */
    public static void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Display success message when user add tasks into list.
     *
     * @param theTask the task added into the list.
     * @param tasks the list of tasks.
     */
    public static void showAdd(Task theTask, TaskList tasks) {
        System.out.println(WORD_ADD);
        System.out.println(theTask.toString());
        int numTask = tasks.getTaskList().size();
        System.out.println("Now you have " + numTask +
                " tasks in the list.");
    }

    public static String returnAdd(Task theTask, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(WORD_ADD).append("\n");
        sb.append(theTask.toString()).append("\n");
        int numTask = tasks.getTaskList().size();
        sb.append("Now you have " + numTask +
                " tasks in the list.");
        return sb.toString();
    }

    /**
     * Display success message when user delete tasks from list.
     *
     * @param theTask the task deleted from the list.
     * @param tasks the list of tasks.
     */
    public static void showDelete(Task theTask, TaskList tasks) {
        System.out.println(WORD_DELETE);
        System.out.println(theTask.toString());
        int numTask = tasks.getTaskList().size();
        System.out.println("Now you have " + numTask +
                " tasks in the list.");
    }

    public static String returnDelete(Task theTask, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(WORD_DELETE).append("\n");
        sb.append(theTask.toString()).append("\n");
        int numTask = tasks.getTaskList().size();
        sb.append("Now you have " + numTask +
                " tasks in the list.");
        return sb.toString();
    }

    /**
     * Display success message when user list all the tasks,
     * or list the tasks on the specific date.
     *
     * @param str a string containing the list of tasks to be displayed.
     * @param action 0 for list, 1 for search.
     */
    public static void showList(String str, int action) {
        if (action == 0) {
            System.out.println(WORD_LIST);
        } else {
            System.out.println(WORD_SEARCH);
        }
        System.out.println(str);
    }

    public static String returnList(String str, int action) {
        StringBuilder sb = new StringBuilder();
        if (action == 0) {
            sb.append(WORD_LIST);
        } else {
            sb.append(WORD_SEARCH);
        }
        sb.append("\n");
        sb.append(str);
        return sb.toString();
    }

    /**
     * Display success message when user mark a task as done or undone.
     *
     * @param theTask the task to be marked.
     * @param action 0 for mark, 1 for unmark.
     */
    public static void showMark(Task theTask, int action) {
        if (action == 0) {
            System.out.println(WORD_MARK_DONE);
        } else {
            System.out.println(WORD_MARK_UNDONE);
        }
        System.out.println(" " + theTask.toString());
    }

    public static String returnMark(Task theTask, int action) {
        StringBuilder sb = new StringBuilder();
        if (action == 0) {
            sb.append(WORD_MARK_DONE);
        } else {
            sb.append(WORD_MARK_UNDONE);
        }
        sb.append("\n");
        sb.append(" " + theTask.toString());
        return sb.toString();
    }

    /**
     * Display success message when user find tasks based on specific pattern.
     * @param str
     */
    public static void showFind(String str) {
        System.out.println(WORD_FIND);
        System.out.println(str);
    }

    public static String returnFind(String str) {
        return WORD_FIND + "\n" + str;
    }

    /**
     * Display goodbye message when Duke session ends.
     */
    public static void showBye() {
        System.out.println(WORD_BYE);
    }
}
