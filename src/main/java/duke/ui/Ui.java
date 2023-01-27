package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * The component that interfaces with the users, handling user commands and printing the application response.
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a sequence of strings in a pretty way.
     *
     * @param chunks sequence of strings to be printed.
     */
    public void respond(String... chunks) {
        System.out.println("    ------------------------------DUKE------------------------------");
        for (String lines : chunks) {
            for (String line: lines.split("\n")) {
                System.out.print("        ");
                System.out.println(line);
            }
        }
        System.out.println("    ------------------------------DUKE------------------------------");
    }

    /**
     * Prints an exception.
     *
     * @param e Exception to be printed.
     */
    public void error(Exception e) {
        System.out.println("    --------------------ERROR-----DUKE-----ERROR--------------------");
        System.out.print("        ");
        System.out.println(e.getMessage());
        System.out.println("    --------------------ERROR-----DUKE-----ERROR--------------------");
    }

    /**
     * Prints a introduction message to the user.
     *
     * @param logo Logo of to be printed with the introduction.
     */
    public void introduce(String logo) {
        respond(
            "Hello I am",
            logo,
            "",
            "What can I do for you?"
        );
    }

    /**
     * Prints a goodbye message and stops receiving user commands.
     */
    public void bye() {
        respond("Bye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Prints a list of tasks.
     *
     * @param tasks List of tasks to be printed.
     */
    public void listTasks(TaskList tasks) {
        respond(
            "Here are the tasks in your list:",
            tasks.toString()
        );
    }

    /**
     * Prints the response after a task has been marked as completed.
     *
     * @param task The task that has been marked as completed.
     */
    public void markTask(Task task) {
        respond(
            "Nice! I've marked this task as done:",
            String.format("=> %s", task
        ));
    }

    /**
     * Prints the response after a task has been marked as uncompleted.
     *
     * @param task The task that has been marked as uncompleted.
     */
    public void unmarkTask(Task task) {
        respond(
            "OK, I've marked this task as not done yet:",
            String.format("=> %s", task)
        );
    }

    /**
     * Prints the response after the addition of a task.
     *
     * @param task Task that was added.
     * @param length Length of the list after addition of task.
     */
    public void addTask(Task task, int length) {
        respond(
            "Got it. I've added this task:",
            String.format("=> %s", task),
            "",
            String.format("Now you have %d tasks in the list", length)
        );
    }

    /**
     * Prints the response after the deletion of a task.
     *
     * @param task Task that was deleted.
     * @param length Length of the list after deletion of task.
     */
    public void deleteTask(Task task, int length) {
        respond(
            "Noted. I've removed this task",
            String.format("=> %s", task),
            "",
            String.format("Now you have %d tasks in the list", length)
        );
    }

    /**
     * Waits and retrieves a command from the user.
     *
     * @return The command of the user.
     */
    public String ask() {
        System.out.print(">>> ");
        String cmd = sc.nextLine();
        System.out.println();
        return cmd;
    }

}
