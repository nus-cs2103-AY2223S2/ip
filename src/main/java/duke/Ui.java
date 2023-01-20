package duke;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);
    }

    public void showArrow() {
        System.out.print("> ");
    }

    public void showSeparator() {
        System.out.println("____________________________________________________________\n");
    }

    public void showErrorMessage(DukeException e) {
        System.out.println(e.getMessage());
    }

    public String readCommand() {
        String command = this.sc.nextLine().trim();
        return command;
    }

    public void showList(TaskList taskStore) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskStore);
    }

    public void showAddTask(Task task, TaskList taskStore) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskStore.getSize() + " tasks in the list.");
    }

    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarkTask(Task task) {
        System.out.println("Got it. I've marked this task as not done:");
        System.out.println(task);
    }

    public void showDeleteTask(Task task, TaskList taskStore) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskStore.getSize() + " tasks in the list.");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showFind(TaskList taskStore) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(taskStore);
    }

    public void closeScanner() {
        this.sc.close();
    }

}
