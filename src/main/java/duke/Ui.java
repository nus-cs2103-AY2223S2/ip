package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Prints the welcome message
     */
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


    /**
     * Gets the next input from the user
     * @return a string consisting of the user input
     */
    public String getNextCommand() {
        String command = sc.nextLine();
        return command;
    }

    /**
     * Print a seperator line
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Print all the tasks
     * @param tasks
     */
    public void printTasks(TaskList tasks) {
        int count = 1;
        for (Task task : tasks.getTasks()) {
            System.out.println(count + "." + task);
            count++;
        }
    }

    /**
     * Print after adding a task
     * @param task
     * @param taskCount
     */
    public void printAddTask(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

    /**
     * Print after deleting a task
     * @param task
     * @param taskCount
     */
    public void printDeleteTask(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

    /**
     * Print after marking a task as done
     * @param task
     */
    public void printTickTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Print after marking a task as not done
     * @param task
     */
    public void printUntickTask(Task task) {
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Print an error
     * @param errMsg
     */
    public void printError(String errMsg) {
        System.out.println(errMsg);
    }

    /**
     * Print the goodbye message
     */
    public void printGoodbyeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
