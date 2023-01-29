package ui;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import dukeexception.DukeException;
import task.Task;

public class Ui {
    Scanner scanner = new Scanner(System.in);

    public void showDivider() {
        System.out.println("\n_______________________________\n");
    }

    public void showError(DukeException error) {
        System.out.println(error.getMessage());
    }

    public void showLoadingError() {

    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

    }

    public void showExit() {
        this.close();
        System.out.println("toodeloo!\n");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public void showTaskAdded(Task task, int tasksSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasksSize + " tasks in the list.");
    }

    public void showTaskRemoved(Task task, int tasksSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasksSize + " tasks in the list.");
    }

    public void showTaskList(List<Task> tasks) {
        IntStream.range(0, tasks.size())
                .forEach((index) -> {
                    System.out.println((index + 1) + ". " + tasks.get(index));
                });
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

}
