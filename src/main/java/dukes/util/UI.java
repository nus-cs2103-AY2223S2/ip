package dukes.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import dukes.util.*;
import dukes.task.*;

import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The util class for reading inputs and providing feedbacks to user.
 */
public class UI {
    // mainly use static methods
    // need to handle the exceptions raised by Parser
    public static final String DIVISHION_LINE = "________________________________________";
    public static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String GREET_WORD = "It's a pleasure to serve you!";
    public static final String BYE_WORD = "Goodbye. Hope you have a nice day!";
    public static final String MARK_DONE_WORD =
            "Nice! You have completed this task: ";
    public static final String MARK_UNDONE_WORD =
            "Well, you have not finished this task yet: ";
    public static final String LIST_WORD = "Here are all of your tasks: ";
    public static final String SEARCH_WORD = "Here are your tasks on the given date: ";
    public static final String ADD_WORD = "This task is added to your list: ";
    public static final String DELETE_WORD = "Ok, I will remove this task for you: ";

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
        System.out.println(LOGO);
        System.out.println(GREET_WORD);
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
        System.out.println(DIVISHION_LINE);
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
        System.out.println(ADD_WORD);
        System.out.println(theTask.toString());
        int numTask = tasks.getTaskList().size();
        System.out.println("Now you have " + numTask +
                " tasks in the list.");
    }

    /**
     * Display success message when user delete tasks from list.
     *
     * @param theTask the task deleted from the list.
     * @param tasks the list of tasks.
     */
    public static void showDelete(Task theTask, TaskList tasks) {
        System.out.println(DELETE_WORD);
        System.out.println(theTask.toString());
        int numTask = tasks.getTaskList().size();
        System.out.println("Now you have " + numTask +
                " tasks in the list.");
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
            System.out.println(LIST_WORD);
        } else {
            System.out.println(SEARCH_WORD);
        }
        System.out.println(str);
    }

    /**
     * Display success message when user mark a task as done or undone.
     *
     * @param theTask the task to be marked.
     * @param action 0 for mark, 1 for unmark.
     */
    public static void showMark(Task theTask, int action) {
        if (action == 0) {
            System.out.println(MARK_DONE_WORD);
        } else {
            System.out.println(MARK_UNDONE_WORD);
        }
        System.out.println(" " + theTask.toString());
    }

    /**
     * Display goodbye message when Duke session ends.
     */
    public static void showBye() {
        System.out.println(BYE_WORD);
    }
}
