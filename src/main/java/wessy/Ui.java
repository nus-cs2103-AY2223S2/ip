package wessy;

import java.util.Scanner;
import wessy.task.Task;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    // HANDLE INPUTS
    boolean hasNextLine() {
        return sc.hasNextLine();
    }

    String readNextLine() {
        return sc.nextLine();
    }

    // MAIN OUTPUT FUNCTIONS
    // Output for "bye"
    void printBye() {
        printMessage("Bye. Hope to see you again soon!");
    }

    // Output for "list" & "find"
    void printListOrFindMessage(String[] tasks, boolean isList) {
        printOpening();
        printListOrFind(tasks, isList);
        printClosing();
    }

    // HELPER
    void printListOrFind(String[] tasks, boolean isList) {
        String extraWord = isList ? "" : "matching ";
        if (tasks.length == 0) {
            if (isList) {
                println("WOOHOO! You do not have any task on the list.");
            } else {
                println("Sorry. We did not find any task that matches the text you entered.");
            }
        } else {
            println("Here are the " + extraWord + "tasks in your list:");
            printMultiln(tasks);
        }
    }

    // Output for "todo", "deadline" & "event
    void printAdded(Task task, int size) {
        String numOfTasks = " " + size + " task";
        if (size > 1) {
            numOfTasks += "s";
        }
        printMessage("Got it. I've added this task:",
                "  " + task, "Now you have" + numOfTasks + " in the list.");
    }

    // Output for "mark" & "unmark"
    void printMarkUnmark(Task chosenTask, boolean isMark) {
        String start = isMark ? "Nice! I've" : "OK, I've";
        if (isMark == chosenTask.checkIsDone()) {
            start = "You have already";
        }
        String end = isMark ? "done:" : "not done yet:";
        printMessage(start + " marked this task as " + end, "  " +
                chosenTask);
    }

    // Output for "delete"
    void printDelete(Task chosenTask, int totalNumOfTasks) {
        printMessage("Noted. I've removed this task:", "  " +
            chosenTask, String.format(
            "Now you have %d task%s in the list.", totalNumOfTasks,
            totalNumOfTasks == 1 ? "" : "s"));
    }

    // Output for "clear"
    void printClear() {
        printMessage("You have cleared your task list. The list is empty now.");
    }

    void printFindMessage(String[] foundResults) {
        printOpening();
        println(" Here are the matching tasks in your list:");

        printClosing();
    }

    // When starting up
    void showWelcome(String[] tasks, int totalNumOfTasks) {
        printOpening();
        println("Hi, I am Wessy, your personal assistant chatbot.");
        println("");
        printListOrFind(tasks, true);
        printClosing();
    }

    // HELPER FUNCTIONS
    static void printOpening() {
        System.out.println("    -Wessy------------------------------" +
                "---------------------------------- ");
    }

    static void printClosing() {
        System.out.println("    -----------------------------------" +
                "----------------------------------- ");
    }

    void println(String str) {
        int length = str.length();
        if (length <= 64) {
            String message = "   |   " + str;
            message += " ".repeat(67 - length);
            message += "|";
            System.out.println(message);
        } else {
            System.out.println("   |   " + str.substring(0, 64) + "   |");
            int remainingLength = length - 64;
            int leftover = remainingLength % 62;
            int n = (int) Math.floor(remainingLength/62);
            for (int i = 0; i < n; i++) {
                System.out.println("   |     " + str.substring(62 * i, 62 * (i + 1)) + "   |");
            }
            String message = "   |     " + str.substring(length - leftover);
            message += " ".repeat(65 - leftover) + "|";
            System.out.println(message);
        }
    }

    void printErr(String str) {
        int length = str.length();
        if (length <= 64) {
            String message = "   |   " + str;
            message += " ".repeat(68 - length);
            message += "|";
            System.err.println(message);
        } else {
            System.err.println("   |   " + str.substring(0, 64) + "    |");
            int remainingLength = length - 64;
            int leftover = remainingLength % 62;
            int n = (int) Math.floor(remainingLength/62);
            for (int i = 0; i < n; i++) {
                System.err.println("   |     " + str.substring(62 * i, 62 * (i + 1)) + "    |");
            }
            String message = "   |     " + str.substring(length - leftover);
            message += " ".repeat(65 - leftover) + "|";
            System.err.println(message);
        }
    }

    void printMultiln(String... linesOfString) {
        for (String line : linesOfString) {
            println(line);
        }
    }

    void printMessage(String... linesOfString) {
        printOpening();
        printMultiln(linesOfString);
        printClosing();
    }

    void handleException(String message) {
        System.err.println("    -Wessy------------------------------" +
                "---------------------------------- ");
        printErr(message);
        System.err.println("    -----------------------------------" +
                "----------------------------------- ");
    }
}
