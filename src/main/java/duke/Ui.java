package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private static Scanner sc = new Scanner(System.in);

    private String line = "____________________________________________________________";
    /**
     * Prints the welcome message
     */
    public String printWelcomeMsg() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        sb.append("Hello from\n" + logo);
        sb.append(line);
        sb.append("Hello! I'm Duke");
        sb.append("What can I do for you?");
        sb.append(line);

        return sb.toString();
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
    public String printLine() {
        return line;
    }

    /**
     * Print all the tasks
     * @param tasks
     */
    public String printTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Task task : tasks.getTasks()) {
            sb.append(count + "." + task);
            sb.append('\n');
            count++;
        }
        return sb.toString();
    }


    public String printFoundTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:");
        sb.append('\n');
        sb.append(printTasks(tasks));
        return sb.toString();
    }

    /**
     * Print after adding a task
     * @param task
     * @param taskCount
     */
    public String printAddTask(Task task, int taskCount) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:");
        sb.append('\n');
        sb.append(task);
        sb.append('\n');
        sb.append("Now you have " + taskCount + " tasks in the list");
        return sb.toString();
    }

    /**
     * Print after deleting a task
     * @param task
     * @param taskCount
     */
    public String printDeleteTask(Task task, int taskCount) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(task);
        sb.append('\n');
        sb.append("Now you have " + taskCount + " tasks in the list");
        return sb.toString();
    }

    /**
     * Print after marking a task as done
     * @param task
     */
    public String printTickTask(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(task);
        return sb.toString();
    }

    /**
     * Print after marking a task as not done
     * @param task
     */
    public String printUntickTask(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("OK! I've marked this task as not done yet:\n");
        sb.append(task.toString());
        return sb.toString();
    }

    /**
     * Print an error
     * @param errMsg
     */
    public String printError(String errMsg) {
        return errMsg;
    }

    /**
     * Print the goodbye message
     */
    public String printGoodbyeMsg() {
        return "Bye. Hope to see you again soon!";
    }
}
