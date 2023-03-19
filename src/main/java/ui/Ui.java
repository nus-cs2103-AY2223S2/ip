package ui;

import java.util.Scanner;

import tasks.Task;
import tasks.TaskList;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private static final String LINE = "-----------------------------------------------------------------------------";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Print the greeting message when booting the program
     */
    public void greet() {
        System.out.println(LINE);
        System.out.println("\tHello! I'm duke.Duke\n\tWhat can I do for you?");
        System.out.println(LINE + "\n");
    }

    public String lastSession() {
        return "Here is your tasks from last session :";
    }

    public String printGreet() {
        return "Hello! I'm duke.\n\tWhat can I do for you?";
    }

    public void taskErrorMsg() {
        System.out.println("\tOOPS!!! Make sure insert all required input.");
    }

    public void todoErrMsg() {
        System.out.println("\tMake sure you follow the correct format. \nFormat: todo {task} \ne.g. todo Play volley");
    }

    /**
     * Message for error in deadline command
     */
    public void deadErrMsg() {
        System.out.println("\tMake sure you follow the correct format. \nFormat: deadline {task} /by {YYYY-MM-DD}"
                + "\ne.g. deadline study /by 2021-12-25");
    }

    /**
     * Message for error in event command
     */
    public void evenErrMsg() {
        System.out.println("\tMake sure you follow the correct format. \nFormat: deadline {task} /from {YYYY-MM-DD} "
                + "/to {YYYY-MM-DD} " + "\ne.g. event midterm /from 2021-12-25 /to 2021-12-31");
    }

    public void idxErrorMsg() {
        System.out.println("\tOOPS!!! Please input the right index.");
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void bye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public String printBye() {
        return "Bye. Hope to see you again soon!";
    }

    public void addTaskMsg() {
        System.out.println("\t" + "Got it. I've added this task:");
    }

    public String printAddTask() {
        return "Got it. I've added this task: ";
    }

    public void markMsg() {
        System.out.println("\tOK, I've marked this task as done:");
    }

    public String printMark() {
        return "OK, I've marked this task as done: ";
    }

    public String printReplace() {
        return "The task has been updated";
    }

    public void unmarkMsg() {
        System.out.println("\tNice! I've unmarked this task as not done yet:");
    }

    public String printUnmark() {
        return "Nice! I've unmarked this task as not done yet: ";
    }

    public void printTask(Task task) {
        System.out.println("\t" + task.toString());
    }

    public void removeMsg() {
        System.out.println("\tNoted. I've removed this task:");
    }

    public String printRemove() {
        return "Noted. I've removed this task: ";
    }

    public void invalidCmdMsg() {
        System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means");
    }

    /**
     * @return message for invalid input
     */
    public String printInvalid() {
        return "OOPS!!! I'm sorry, but I don't know what that means\nList of valid Requests:\n"
                + "1. todo {task}\n"
                + "2. deadline {task} /by {YYYY-MM-DD}\n"
                + "3. event {task} /from {YYYY-MM-DD} /to {YYYY-MM-DD}\n"
                + "4. mark {index}\n"
                + "5. unmark {index}\n"
                + "6. list\n"
                + "7. find {key}\n" + "8. update {index} {new task}";
    }

    public void printListSize(TaskList taskList) {
        System.out.println("\t" + "Now you have " + taskList.getSize() + " tasks in the list");
    }

    /**
     * Message that popped if previous session have data
     */
    public void saveTaskMsg() {
        System.out.println(LINE);
        System.out.println("\tSaved Tasks From Last Session");
    }

    /**
     * @return message for error input
     */
    public String errParse() {
        return "Please input a valid request\nList of valid Requests:\n"
                + "1. todo {task}\n"
                + "2. deadline {task} /by {YYYY-MM-DD}\n"
                + "3. event {task} /from {YYYY-MM-DD} /to {YYYY-MM-DD}\n"
                + "4. mark {index}\n" + "5. unmark {index}\n"
                + "6. list\n"
                + "7. find {key}\n"
                + "8. update {index} {new task}";
    }

    public void outOfBound() {
        System.out.println("Index is out of bound!");
    }
}
