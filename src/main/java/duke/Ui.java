package duke;


import java.util.Scanner;

import duke.task.Task;

public class Ui{
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void showLine() {
        System.out.println("----------------------------------------");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }
    }

    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showAdd(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showDelete(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showLoadingError() {
        System.out.println("Error loading file!");
    }

    public void showSavingError() {
        System.out.println("Error saving file!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return sc.nextLine();
    }
}