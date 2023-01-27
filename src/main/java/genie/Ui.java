package genie;

import genie.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final static String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|";
    // credits to: https://patorjk.com/
    private final static String genieLogo =
                            "     *          *          *              *     *         *    *\n " +
                            "               ▄████ ▓█████  ███▄ * ▒█ ▒██ ▓█████               \n" +
                            " *       *    ▄▒█▀ ▀█ ▒██  ▀  ▒██▀█▄ ▒█ ▄▄▄ ▒▓█  ▀ *          * \n" +
                            "              ▒██ ▄▄▄ ▒██▒▄   ▒██ ▀▒ ▒█ ▒██ ▒██▒▄       *       \n" +
                            "       *      ░▓█ ▀░█ ░▓█  ▄  ░██  ░▌░█ ░██ ░▓█  ▄   *          \n" +
                            "   *       *  ░▒▓███▀ ░▒████  ░██ * ░██ ░██ ░▒████         *    \n" +
                            //           ░▒   ▒ ░░ ▒░ ░░ ▒░   ▒ ▒ ░▓  ░░ ▒░ ░\n" +
                            //         *      ░   ░  ░ ░  ░░ ░░  *░ ░░ ░ ░ ░ ░  ░  *    \n" +
                            "       *    * ░ ░   ░    ░ *    ░   ░ ░  ░ ░   ░ *      *       \n" +
                            " *       *       *  ░    ░  ░     *   ░  ░    *░  ░   *       *   ";
    private final static String endBanner =
                    " ░▒   ▒ ░░ ▒░ ░░ ▒░   ▒ ▒ ░▓  ░░ ▒░ ░   ░▒   ▒ ░░ ▒░ ░░ ▒░   ▒ ▒ ░▓  ░░ ▒░ ░\n" +
                    "  ░   ░  ░ ░  ░░ ░░   ░ ▒░ ▒ ░ ░ ░  ░    ░   ░  ░ ░  ░░ ░░   ░ ▒░ ▒ ░ ░ ░  ░\n" +
                    "░ ░   ░    ░      ░   ░ ░  ▒ ░   ░     ░ ░   ░    ░      ░   ░ ░  ▒ ░   ░   \n" +
                    "      ░    ░  ░         ░  ░     ░  ░        ░    ░  ░         ░  ░     ░  ░";
    public Ui() {}
    public void greet() {
        System.out.println("Hello! This is genie.Genie, your personal task tracker!");
    }
    public void bootLogo() {
        printLine();
        System.out.println(genieLogo);
        printLine();
    }
    public void printLine() {
        //System.out.println("_____________________________________________________________________");
        System.out.println("==============================================================================");
        //System.out.println("~-~-~-~-~-~-~-~-~~-~-~-~-~-~-~-~-~~-~-~-~-~-~-~-~-~~-~-~-~-~-~-~-~-~~-~-~-~-~-~-~-~-~");
    }
    public void printList(ArrayList<Task> tasks) {
        if(tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for(int i = 1; i <= tasks.size(); i++) {
                Task t = tasks.get(i - 1);
                System.out.println(i + ". " + t.toString());
            }
        } else {
            showEmptyListMessage();
        }
        printLine();
    }
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public void showMarkDoneMessage(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" + "  " + t.toString());
        printLine();
    }
    public void showAddTaskMessage(Task t, int size) {
        System.out.println("Got it. I've added this task:\n  " + t.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        printLine();
    }
    public void showUnmarkDoneMessage(Task t) {
        System.out.println("Okay, I've marked this task as not done yet:\n" + "  " + t.toString());
        printLine();
    }
    public void showDeleteTaskMessage(Task t, int size) {
        System.out.println("Noted. I've removed this task:\n" + "  " + t.toString() +
                "\nNow you have " + size + " tasks in the list.");
        printLine();
    }
    public void showFarewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public String showErrorMessage() {
        return "Something went wrong here xx...";
    }
    public void printLoadedTaskList(ArrayList<String> tl) {
        if(tl.isEmpty()) {
            showEmptyListMessage();
        } else {
            System.out.println("Here is a record of your task list from where you had previously left off:");
            for (int i = 0; i < tl.size(); i++) {
                System.out.println("  " + tl.get(i));
            }
            System.out.println("Now, what can I do for you?");
        }
        printLine();
    }
    public void showEmptyListMessage() {
        System.out.println("Your task list is currently empty! Let's get started! ^-^");
    }
}
