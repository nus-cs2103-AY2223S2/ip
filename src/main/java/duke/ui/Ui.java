package duke.ui;

import java.util.Scanner;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private Scanner in = new Scanner(System.in);

    public void printWelcomeMessage() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    public void printByeMessage() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
        in.close();
    }

    public void printTaskAdded(Task task, int taskCount) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
    }

    public void printTaskMarked(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task);
    }

    public void printTaskUnmarked(Task task) {
        System.out.println("\tNice! I've marked this task as not done:");
        System.out.println("\t\t" + task);
    }

    public void printTaskDeleted(Task task, int taskCount) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
    }

    public void printTaskList(TaskList taskList) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task t = taskList.getTask(i);
            System.out.println("\t\t" + (i + 1) + "." + t);
        }
    }

    public void printError(DukeException e) {
        // System.out.println("\t____________________________________________________________");
        System.out.println("\t" + e.getMessage());
        // System.out.println("\t____________________________________________________________");
    }

    public void printErrorActionMessage() {
        // System.out.println("\t____________________________________________________________");
        System.out.println("\tI'm sorry, but I don't know what that means :-(");
        // System.out.println("\t____________________________________________________________");
    }

    public String readCommand() {
        return in.nextLine();
    }
}
