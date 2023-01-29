import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static Scanner sc = new Scanner(System.in);

    public void printWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }


    public String getNextCommand() {
        String command = sc.nextLine();
        return command;
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printTasks(TaskList tasks) {
        int count = 1;
        for (Task task : tasks.getTasks()) {
            System.out.println(count + "." + task);
            count++;
        }
    }

    public void printAddTask(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

    public void printDeleteTask(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

    public void printTickTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void printUntickTask(Task task) {
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println(task);
    }
    public void printError(String errMsg) {
        System.out.println(errMsg);
    }

    public void printEmptyDescriptionError(String type) {
        System.out.println("☹ OOPS!!! The description of a " + type + " cannot be empty.");
    }

    public void printGoodbyeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
