package utilities;

import collections.TaskList;
import exceptions.SundayException;
import task.Task;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

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
    public String[] readCommand() {
        String command = this.sc.next();
        String input = this.sc.nextLine();
        String[] fullCommand = new String[2];
        fullCommand[0] = command;
        fullCommand[1] = input;
        return fullCommand;
    }
    public void close() {
        this.sc.close();
        dummyStream.close();
        defaultStream.close();
    }
    public static void setDummyStream() {
        System.setOut(dummyStream);
    }
    public static void setDefaultStream() {
        System.setOut(defaultStream);
    }
    private static void printBar() {
        System.out.println(indent + bar);
    }
    private static void printText(String text) {
        System.out.println(indent + text);
    }
    public static void printWelcome() {
        printBar();
        printText("Hi! I'm Sunday, pleasure to meet you!");
        printText("How can I help?");
        printBar();
    }
    public static void printCreatedSaveFile() {
        printBar();
        printText("It appears we haven't met!");
        printText("Start typing away your tasks and I'll note them down accordingly :)");
        printBar();
    }
    public static void printLoadedSaveFile() {
        printBar();
        printText("It appears we've met! I've restored your task list from our last session.");
        printBar();
    }
    public static void printTaskList(TaskList taskList) {
        printBar();
        printText("Here's everything I've noted down for you:");

        String[] strArr = taskList.toString().split("\n");
        for (int i = 0; i < strArr.length; i++) {
            printText(indent + (i+1) + ". " + strArr[i]);
        }

        printBar();
    }
    public static void printAddedTask(Task task, int uncompletedSize) {
        Ui.printBar();
        Ui.printText("Got it. I've added this task:");
        Ui.printText("  " + task.toString());
        Ui.printText("Now you have " + uncompletedSize + " task(s) in the list.");
        Ui.printBar();
    }
    public static void printMarkedTask(Task task, int uncompletedSize) {
        Ui.printBar();
        Ui.printText("Well Done! I've marked this task as done:");
        Ui.printText("  " + task.toString());
        Ui.printText("Now you have " + uncompletedSize + " task(s) in the list.");
        Ui.printBar();
    }
    public static void printUnmarkedTask(Task task, int uncompletedSize) {
        printBar();
        printText("OK, I've marked this task as not done yet:");
        printText("  " + task);
        printText("Now you have " + uncompletedSize + " task(s) in the list.");
        printBar();
    }
    public static void printDeletedTask(Task task, int uncompletedSize) {
        Ui.printBar();
        Ui.printText("Noted. I've removed this task:");
        Ui.printText("  " + task.toString());
        Ui.printText("Now you have " + uncompletedSize + " task(s) in the list.");
        Ui.printBar();
    }
    public static void printException(SundayException e) {
        printBar();
        printText(e.getMessage());
        printBar();
    }
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
    public static void printEmptyTaskList() {
        printBar();
        printText("Your list is currently empty.");
        printText("Tell me what to note down and I'll remember it accordingly!");
        printBar();
    }
    public static void printListFound(TaskList taskList) {
        printBar();
        if (taskList.isEmpty()) {
            printText("Looks like you don't have any tasks matching that description.");
        } else {
            Ui.printText("Here are the task(s) I've found:");
            String[] strArr = taskList.toString().split("\n");
            for (int i = 0; i < strArr.length; i++) {
                printText(indent + (i+1) + ". " + strArr[i]);
            }
        }
        printBar();
    }
}
