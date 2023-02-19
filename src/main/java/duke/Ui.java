package duke;

import duke.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {

    public static void ShowAddMessage(Task taskNum, int size) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + taskNum);
        System.out.println("\tNow you have " + size + " tasks in the list.");
    }

    public static void ShowDeleteMessage(Task taskNum, int size) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + taskNum);
        System.out.println("\tNow you have " + size + " tasks in the list.");
    }

    public static void ShowList(ArrayList<Task> tasks) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int taskNum = i + 1;
            System.out.printf("\t%d. %s\n", taskNum, tasks.get(i));
        }
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public void showError(String msg) {
        System.out.println(msg);
    }

    public static void showLoadingError() {
        System.out.println("\tCannot load file.\n");
    }

    public static void showGreeting() {
        System.out.println("Hello! I'm duke.Duke\n\tWhat can I do for you?");
    }

    public static void showExit() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public static void showMessage(String msg) {
        System.out.println(msg);
    }
}