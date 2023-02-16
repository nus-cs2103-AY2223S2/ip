package utilities;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import collections.TaskList;
import exceptions.SundayException;
import task.Task;

/**
 * The Ui class is a user interface for the Sunday task manager.
 * It contains methods for reading user input, printing output to the console, and closing the user interface.
 *
 * @author Tan Yan-Hao Joshua
 */
public class Ui {

    private static String bar = "____________________________________________________________";

    private static String indent = "    ";

    private static PrintStream dummyStream = new PrintStream(new OutputStream() {
        @Override
        public void write(int b) throws IOException {
            // Empty to hide prints while reading from file
        }
    });

    private static PrintStream defaultStream = System.out;

    private Scanner sc = new Scanner(System.in);

    /**
     * Reads a user's command as a string array containing two elements.
     * The first element is the command and the second is the input.
     *
     * @return The user's command as a string array.
     */
    public String[] readCommand() {
        String command = this.sc.next();
        String input = this.sc.nextLine();
        String[] fullCommand = new String[2];
        fullCommand[0] = command;
        fullCommand[1] = input;
        return fullCommand;
    }

    /**
     * Closes the user interface.
     */
    public void close() {
        this.sc.close();
        dummyStream.close();
        defaultStream.close();
    }

    /**
     * Sets the output stream to a dummy stream.
     */
    public static void setDummyStream() {
        System.setOut(dummyStream);
    }

    /**
     * Sets the output stream back to the default stream.
     */
    public static void setDefaultStream() {
        System.setOut(defaultStream);
    }

    /**
     * Prints a bar to the console.
     */
    private static void printBar() {
        System.out.println(indent + bar);
    }

    /**
     * Prints text to the console.
     *
     * @param text The text to be printed.
     */
    private static void printText(String text) {
        System.out.println(indent + text);
    }

    /**
     * Prints a welcome message to the console.
     */
    public static void printWelcome() {
        printBar();
        printText("Hi! I'm Sunday, pleasure to meet you!");
        printText("How can I help?");
        printBar();
    }

    /**
     * Prints a message to the console indicating that a new save file has been created.
     */
    public static void printCreatedSaveFile() {
        printBar();
        printText("It appears we haven't met!");
        printText("Start typing away your tasks and I'll note them down accordingly :)");
        printBar();
    }

    /**
     * Prints a message to the console indicating that a saved file has been loaded.
     */
    public static void printLoadedSaveFile() {
        printBar();
        printText("It appears we've met! I've restored your task list from our last session.");
        printBar();
    }

    /**
     * Prints a task list to the console.
     *
     * @param taskList The task list to be printed.
     */
    public static void printTaskList(TaskList taskList) {
        printBar();
        printText("Here's everything I've noted down for you:");

        String[] strArr = taskList.toString().split("\n");
        for (int i = 0; i < strArr.length; i++) {
            printText(indent + (i + 1) + ". " + strArr[i]);
        }

        printBar();
    }

    /**
     * Prints a message to the console indicating that a task has been added to the task list.
     *
     * @param task The task that has been added.
     * @param uncompletedSize The number of uncompleted tasks in the task list.
     */
    public static void printAddedTask(Task task, int uncompletedSize) {
        Ui.printBar();
        Ui.printText("Got it. I've added this task:");
        Ui.printText("  " + task.toString());
        Ui.printText("Now you have " + uncompletedSize + " task(s) in the list.");
        Ui.printBar();
    }

    /**
     * Prints a message to the console indicating that a task has been marked as complete.
     *
     * @param task The task that has been marked.
     * @param uncompletedSize The number of uncompleted tasks in the task list.
     */
    public static void printMarkedTask(Task task, int uncompletedSize) {
        Ui.printBar();
        Ui.printText("Well Done! I've marked this task as done:");
        Ui.printText("  " + task.toString());
        Ui.printText("Now you have " + uncompletedSize + " task(s) in the list.");
        Ui.printBar();
    }

    /**
     * Prints a message to the console indicating that a task has been unmarked.
     *
     * @param task The task that has been unmarked.
     * @param uncompletedSize The number of uncompleted tasks in the task list.
     */
    public static void printUnmarkedTask(Task task, int uncompletedSize) {
        printBar();
        printText("OK, I've marked this task as not done yet:");
        printText("  " + task);
        printText("Now you have " + uncompletedSize + " task(s) in the list.");
        printBar();
    }

    /**
     * Prints a message to the console indicating that a task has been deleted from the task list.
     *
     * @param task The task that has been deleted.
     * @param uncompletedSize The number of uncompleted tasks in the task list.
     */
    public static void printDeletedTask(Task task, int uncompletedSize) {
        Ui.printBar();
        Ui.printText("Noted. I've removed this task:");
        Ui.printText("  " + task.toString());
        Ui.printText("Now you have " + uncompletedSize + " task(s) in the list.");
        Ui.printBar();
    }

    /**
     * Prints an error message to the console.
     *
     * @param e The exception that was thrown.
     */
    public static void printException(SundayException e) {
        printBar();
        printText(e.getMessage());
        printBar();
    }

    /**
     * Prints a goodbye message to the console.
     *
     * @param didSave Indicates whether the task list was saved before the program exited.
     */
    public static void printGoodbye(boolean didSave) {
        printBar();
        if (didSave) {
            printSavedToFile();
        }
        printText("Goodbye and have a pleasant day!");
        printBar();
    }

    private static void printSavedToFile() {
        Ui.printText("Okay, I've save your list for the next session!");
    }

    /**
     * Prints a message to the console indicating that the task list is empty.
     */
    public static void printEmptyTaskList() {
        printBar();
        printText("Your list is currently empty.");
        printText("Tell me what to note down and I'll remember it accordingly!");
        printBar();
    }

    /**
     * Prints the task list in a specific format to the console.
     *
     * @param taskList The task list to be printed.
     */
    public static void printListFound(TaskList taskList) {
        printBar();
        if (taskList.isEmpty()) {
            printText("Looks like you don't have any tasks matching that description.");
        } else {
            Ui.printText("Here are the task(s) I've found:");
            String[] strArr = taskList.toString().split("\n");
            for (int i = 0; i < strArr.length; i++) {
                printText(indent + (i + 1) + ". " + strArr[i]);
            }
        }
        printBar();
    }
}
