package duke;

import java.util.Scanner;

public class Ui {

    final static String PARTITION = "*******************************************";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showPartition() {
        System.out.println(PARTITION);
    }

    public void showWelcome() {
        this.showPartition();
        System.out.println("Hello! I'm Anton's Bot");
        System.out.println("What can I do for you?");
        this.showPartition();
    }

    public void showBye() {
        this.showPartition();
        System.out.println("Bye. Hope to see you again soon!");
        this.showPartition();
    }

    public void showCreateNewFile() {
        System.out.println("Creating new file...");
    }

    public String[] readLine() {
        String currentInput = scanner.nextLine();
        String[] currentInputArray = currentInput.split(" ", 2);
        return currentInputArray;
    }

    public void showList(TaskList listOfTasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task currentTask = listOfTasks.get(i);
            System.out.println(String.format("%d.%s", i + 1, currentTask.toString()));
        }
    }
    public void showFind(TaskList filteredList) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < filteredList.size(); i++) {
            Task currentTask = filteredList.get(i);
            System.out.println(String.format("%d.%s", i + 1, currentTask.toString()));
        }
    }

    public void showAdd(TaskList listOfTasks, String taskName) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskName);
        System.out.println(String.format("Now you have %d tasks in the list.", listOfTasks.size()));
    }

    public void showUnmark(String taskName) {
        System.out.println("  OK, I've marked this task as not done yet:");
        System.out.println(taskName);
    }

    public void showMark(String taskName) {
        System.out.println("  Nice! I've marked this task as done:");
        System.out.println(taskName);
    }



}
