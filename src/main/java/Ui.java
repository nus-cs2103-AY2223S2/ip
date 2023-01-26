import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {
    private static final String STR = "------------------------------------------------------------";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu kk:mm");
    private static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String nextInput() {
        return this.sc.nextLine();
    }

    public void welcomeResponse() {
        System.out.println(LOGO + "Hello! I'm Duke\n");
    }

    public void successfulLoadResponse() {
        System.out.println("I've successfully retrieved your past task list!");
    }

    public void askForTaskResponse() {
        System.out.println("What can I do for you today?");
    }

    public void listTaskResponse(TaskList tasks) {
        if(tasks.getSize() == 0) {
            printResponse("You have 0 task to complete at the moment!");
        } else {
            printResponse("Here are the task in your list: \n" + tasks.toStringList() + "\nYou have " + tasks.getSize()
                    + " tasks in the list.");
        }
    }

    public void markTaskResponse(Task t) {
        printResponse("Nice! I've marked this task as done: \n " + t);
    }

    public void unmarkTaskResponse(Task t) {
        printResponse("OK, I've marked this task as not done yet \n" + t);
    }

    public void deleteTaskResponse(Task t, TaskList lst) {
        printResponse("Noted. I've removed this task: \n" + t + "\nNow you have " + lst.getSize() + " "
                + "tasks in the list.");
    }

    public void addTaskResponse(Task t, TaskList lst) {
        printResponse("Got it. I've added this task: \n" + t + "\nNow you have " + lst.getSize()
                + " tasks in the list.");
    }

    public void exitResponse() {
        printResponse("Bye. Hope to see you again soon!");
    }

    public void taskNotChosenErrorMessage() {
        printResponse("OPPS! You have not selected a task.");
    }

    public void unableToSaveErrorMessage() {
        printResponse("Sorry, memory cannot be saved!");
    }

    public void unreadableCommandErrorMessage() {
        printResponse("I'm sorry, but I don't understand what that means! Try re-typing your instruction!");
    }

    public void loadingErrorMessage() {
        System.out.println("Sorry! I was unable to load your task list from memory!");
    }

    public void incompleteCommandErrorMessage() {
        printResponse("OOPS!!! The description of this deadline is incomplete.");
    }
    private static void printResponse(String response) {
        System.out.println(STR);
        System.out.println(response);
        System.out.println(STR);
    }

}
