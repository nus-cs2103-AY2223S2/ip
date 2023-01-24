package duke;

import duke.tasks.*;

import java.util.Scanner;
public class Ui {
    private Scanner sc = new Scanner(System.in);

    public String getUserInput() {
        return this.sc.nextLine();
    }

    public void closeInput() {
        this.sc.close();
    }

    public void printLine() {
        System.out.println("------------------------------------------------------------------");
    }

    public void initialDisplay() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void taskAddDisplay(Task task, int listLength) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + task.toString());
        displayTasks(listLength);
    }

    public void taskDeleteDisplay(TaskList list, int taskIndex) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + list.getTask(taskIndex).toString());
    }

    public void displayList(TaskList list) {
        int listSize = list.listLength();
        for(int i = 1; i <= listSize; i++) {
            System.out.println("\t" + i + ". " + list.getTask(i).toString());
        }
    }

    public void displayTasks(int listLength) {
        System.out.println(String.format("\tNow you have %d tasks in the list.", listLength));
    }

    public void unmarkTaskDisplay(Task task) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + task.toString());
    }

    public void markTaskDisplay(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task.toString());
    }

    public void exitDisplay() {
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    public void noMatchFoundDisplay() {
        System.out.println("\tNo matches found...");
    }

    public void matchFoundDisplay(TaskList list) {
        System.out.println("\tHere are the matching tasks in your list:");
        displayList(list);
    }
}
