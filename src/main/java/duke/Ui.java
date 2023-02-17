package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private static final Scanner sc = new Scanner(System.in);

    private final String line = "____________________________________________________________";

    /**
     * Gets the next input from the user
     * @return a string consisting of the user input
     */
    public String getNextCommand() {
        return sc.nextLine();
    }

    /**
     * Print all the tasks
     * @param tasks to print
     */
    public String printTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Task task : tasks.getTasks()) {
            sb.append(count).append(".").append(task);
            sb.append('\n');
            count++;
        }
        return sb.toString();
    }


    public String printFoundTasks(TaskList tasks) {
        return "Here are the matching tasks in your list:" +
                '\n' +
                printTasks(tasks);
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
        sb.append("Now you have ").append(taskCount).append(" tasks in the list");
        return sb.toString();
    }

    /**
     * Print after deleting a task
     * @param task
     * @param taskCount
     */
    public String printDeleteTask(Task task, int taskCount) {
        return "Noted. I've removed this task:\n" +
                task +
                '\n' +
                "Now you have " + taskCount + " tasks in the list";
    }

    /**
     * Print after marking a task as done
     * @param task
     */
    public String printTickTask(Task task) {
        return "Nice! I've marked this task as done:\n" +
                task;
    }

    /**
     * Print after marking a task as not done
     * @param task
     */
    public String printUntickTask(Task task) {
        return "OK! I've marked this task as not done yet:\n" +
                task.toString();
    }

    /**
     * Print the goodbye message
     */
    public String printGoodbyeMsg() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Print error message
     * @param err message to be printed
     * @return
     */
    public String printError(String err) {
        return err;
    }

    public String printSnoozeTask(Task task) {
       return "OK! I've snoozed this task:\n" + task;
    }

    public String printUnsnoozeTask(Task task) {
        return "OK! I've unsnoozed this task:\n" + task;
    }
}
