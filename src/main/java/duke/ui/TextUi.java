package duke.ui;

import duke.Duke;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class TextUi {

    private Duke duke;
    private final Scanner in;
    private static int numberOfInput = 0;
    private static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String NEXT_RESPONSE = "\nWhat else can I do for you?";

    public TextUi() {
        in = new Scanner(System.in);
        this.duke = duke;
    }

    public String printGreetings() {
        return "Hello! What can I do for you?";
    }

    public String printGoodbye() {
        String str = "Bye. Hope to see you again soon! :-p";
        return str;
    }

    public String printError(String str) {
        System.out.println(str + NEXT_RESPONSE);
        return str + NEXT_RESPONSE;
    }

    public String printTaskAdded(Task t, TaskList tasks) {
        System.out.println("Got it, I've added this task:");
        System.out.println("    " + t);
        String str = String.format("Now you have %d tasks in your list.", tasks.getNumTasks());
        System.out.println(str + NEXT_RESPONSE);
        return ("Got it, I've added this task:\n" + t.toString() + "\n" + str + NEXT_RESPONSE);
    }

    public String printDeletedTask(Task t, TaskList tasks) {
        System.out.println("Okay, I've removed this task:");
        System.out.println("    " + t);
        String str = String.format("Now you have %d tasks in your list.", tasks.getNumTasks());
        System.out.println(str + NEXT_RESPONSE);
        return ("Okay, I've removed this task:\n    " + t.toString() + "\n" + str + NEXT_RESPONSE);
    }

    public String printMarkedTask(Task t, TaskList tasks) {
        System.out.println("Great job on completing this task! I've marked it as done:");
        System.out.println("    " + t + NEXT_RESPONSE);
        return ("Great job on completing this task!\nI've marked it as done:\n"
                + "    " + t.toString() + NEXT_RESPONSE);
    }

    public String printUnmarkedTask(Task t, TaskList tasks) {
        System.out.println("Alright, I've marked this task as not done yet:");
        System.out.println("    " + t + NEXT_RESPONSE);
        return ("Alright, I've marked this task as not done yet:\n" + "    "
                + t.toString() + NEXT_RESPONSE);
    }

    public String printSearched(String keyword, TaskList matched) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(matched + NEXT_RESPONSE);
        return ("Here are the matching tasks in your list:\n" + matched + NEXT_RESPONSE);
    }

    public String printTaskList(TaskList tasks) {
        System.out.println(tasks + NEXT_RESPONSE);
        return (tasks.toString() + NEXT_RESPONSE);
    }

    public String printNext() {
        String str = "What else can I do for you?";
        System.out.println(str + NEXT_RESPONSE);
        return(str + NEXT_RESPONSE);
    }

    public String getUserCommand() {
        if (numberOfInput == 0) {
            System.out.println("What can I do for you?");
        } else {
            System.out.println("What else can I do for you?");
        }
        numberOfInput++;
        String input = in.nextLine();
        return input;
    }
}
