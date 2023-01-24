package duke;

import java.util.Scanner;

public class Ui {
    private static Scanner scanner = new Scanner(System.in);
    private static final String DIVIDER = "____________________________________________________________";

    //method for stdout
    protected void printHorizontalLine() {
        System.out.println("\t" + DIVIDER);
    }

    public void showWelcomeMessage () {
        printHorizontalLine();
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        printHorizontalLine();
    }

    public void showGoodbyeMessage () {
        //closeScanner();
        printHorizontalLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public void responseToListCommand (TaskList taskList) {
        printHorizontalLine();
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            int tmp = i + 1;
            System.out.println("\t" + tmp + "." + taskList.getTaskAt(i));
        }
        printHorizontalLine();
    }

    public void responseToLAddTaskCommand (Task task, TaskList taskList) {
        printHorizontalLine();
        System.out.println("\tGot it. I've added this task:\n\t  " + task + "\n\tNow you have "
                + taskList.size() + " tasks in the list.");
        printHorizontalLine();
    }

    public void responseToMarkTaskCommand (Task task) {
        printHorizontalLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task);
        printHorizontalLine();
    }

    public void responseToUnmarkTaskCommand (Task task) {
        printHorizontalLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + task);
        printHorizontalLine();
    }

    public void responseToDeleteTaskCommand (TaskList taskList, int index) {
        printHorizontalLine();
        System.out.println("\tNoted. I've removed this task:\n\t  " + taskList.getTaskAt(index)
                + "\n\tNow you have " + taskList.size() + " tasks in the list.");
        printHorizontalLine();
    }

    public void showLoadingError() {
        printHorizontalLine();
        System.out.println("\t☹ OOPS!!! Unable to load data from the file");
        printHorizontalLine();
    }

    public void showError(String msg) {
        printHorizontalLine();
        System.out.println("\t☹ OOPS!!! "+ msg);
        printHorizontalLine();
    }


    public String requestForUserInput() {
        System.out.println("\tEnter Command: ");
        return scanner.nextLine();
    }

    private void closeScanner() {
        scanner.close();
    }

}
