package duke;

import duke.Tasks.Task;
import duke.Tasks.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showLine() {
        System.out.println("-".repeat(20));
    }
    public void showLoadingError() {
        System.out.println("☹ OOPS!!! I'm sorry, but I cannot find the directory!");
    }


    public void showMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    public void showStored(TaskList tasks) {
        System.out.println("File has been stored!");
        System.out.println(tasks);
    }

    public void showDeleted(Task task) {
        System.out.println("This task has been deleted successfully");
        System.out.println(task);
    }

    public void showCommandError(String word, Exception exception) {
        System.out.println(exception.toString());
        System.out.println("duke.Command: " + word);
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void listAll(TaskList tasks) {
        System.out.println(tasks.toFormattedString());
    }
}
