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
    public static final String WORD_LIST = "Here are all of your tasks: ";
    public static final String WORD_SEARCH = "Here are your tasks on the given date: ";
    public static final String WORD_ADD = "This task is added to your list: ";
    public static final String WORD_DELETE = "Ok, I will remove this task for you: ";

    public static String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    public static void showWelcome() {
        showLine();
        System.out.println(WORD_LOGO);
        System.out.println(WORD_GREET);
        showLine();
    }

    public static void showLoadingError(String msg) {
        System.out.println(msg);
    }

    public static void showLine() {
        System.out.println(WORD_DIVISHION_LINE);
    }

    public static void showError(String msg) {
        System.out.println(msg);
    }

    public static void showAdd(Task theTask, TaskList tasks) {
        System.out.println(WORD_ADD);
        System.out.println(theTask.toString());
        int numTask = tasks.getTaskList().size();
        System.out.println("Now you have " + numTask +
                " tasks in the list.");
    }

    public static void showDelete(Task theTask, TaskList tasks) {
        System.out.println(WORD_DELETE);
        System.out.println(theTask.toString());
        int numTask = tasks.getTaskList().size();
        System.out.println("Now you have " + numTask +
                " tasks in the list.");
    }

    public static void showList(String str, int action) {
        if (action == 0) {
            System.out.println(WORD_LIST);
        } else {
            System.out.println(WORD_SEARCH);
        }
        System.out.println(str);
    }

    public static void showMark(Task theTask, int action) {
        if (action == 0) {
            System.out.println(WORD_MARK_DONE);
        } else {
            System.out.println(WORD_MARK_UNDONE);
        }
        System.out.println(" " + theTask.toString());
    }

    public static void showBye() {
        System.out.println(WORD_BYE);
    }
}
