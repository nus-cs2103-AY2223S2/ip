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
    public static final String FIND_WORD = "Here are the matching tasks in list:  ";
    public static final String ADD_WORD = "This task is added to your list: ";
    public static final String DELETE_WORD = "Ok, I will remove this task for you: ";

    public static String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    public static void showWelcome() {
        showLine();
        System.out.println(LOGO);
        System.out.println(GREET_WORD);
        showLine();
    }

    public static void showLoadingError(String msg) {
        System.out.println(msg);
    }

    public static void showLine() {
        System.out.println(DIVISHION_LINE);
    }

    public static void showError(String msg) {
        System.out.println(msg);
    }

    public static void showAdd(Task theTask, TaskList tasks) {
        System.out.println(ADD_WORD);
        System.out.println(theTask.toString());
        int numTask = tasks.getTaskList().size();
        System.out.println("Now you have " + numTask +
                " tasks in the list.");
    }

    public static void showDelete(Task theTask, TaskList tasks) {
        System.out.println(DELETE_WORD);
        System.out.println(theTask.toString());
        int numTask = tasks.getTaskList().size();
        System.out.println("Now you have " + numTask +
                " tasks in the list.");
    }

    public static void showList(String str, int action) {
        if (action == 0) {
            System.out.println(LIST_WORD);
        } else {
            System.out.println(SEARCH_WORD);
        }
        System.out.println(str);
    }

    public static void showMark(Task theTask, int action) {
        if (action == 0) {
            System.out.println(MARK_DONE_WORD);
        } else {
            System.out.println(MARK_UNDONE_WORD);
        }
        System.out.println(" " + theTask.toString());
    }

    public static void showFind(String str) {
        System.out.println(FIND_WORD);
        System.out.println(str);
    }

    public static void showBye() {
        System.out.println(BYE_WORD);
    }
}
