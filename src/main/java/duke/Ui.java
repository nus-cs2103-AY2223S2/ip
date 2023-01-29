package duke;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages I/O functionality of Duke.
 */
public class Ui {
    Scanner myScanner;
    TaskList myTaskList;

    /**
     * Constructor for a Ui object.
     * @param myTaskList
     */
    public Ui(TaskList myTaskList) {
        this.myScanner = new Scanner(System.in);
        this.myTaskList = myTaskList;
    }

    /**
     * Scans for user input.
     * @return string that user inputs
     */
    public String getNextTask() {
        return this.myScanner.nextLine();
    }

    /**
     * Greets user with a custom greeting.
     */
    public void greetUser() {
        System.out.println("  Insert ingenious greeting here");
    }

    /**
     * Prints a task to standard output.
     * @param t any task
     */
    private void printNewTask(Task t) {
        if (t instanceof Event) {
            System.out.println("  new event added!");
        } else if (t instanceof Deadline) {
            System.out.println("  new deadline added!");
        } else if (t instanceof ToDo) {
            System.out.println("  new todo added!");
        }
        System.out.println("    " + t.toString());
        System.out.println("  Now you have " + String.valueOf(this.myTaskList.countTasks()) +
                " tasks in the list!");
    }

    /**
     * Prints a reply to standard output when the user does not enter a task.
     * @param inputCommand What the user inputs
     */
    public void printReply(String inputCommand) {
        switch (inputCommand) {
            case "list":
                for (int i = 0; i < myTaskList.countTasks(); i++) {
                    System.out.println("  " + String.valueOf(i + 1) + ". " + myTaskList.getTaskAtIndex(i));
                }
                break;
            case "bye":
                System.out.println("  See you again");
                break;
            default:
                System.out.println("  this is not a task, contact admin");
                break;
        }
    }
    /**
     * Prints a reply to standard output given a command to add, mark or delete a task.
     * @param inputCommand What the user inputs
     */
    public void printReply(String inputCommand, Task currTask) throws EmptyDescriptionException {
        switch (inputCommand) {
        case "mark":
            System.out.println("  You are done with: ");
            System.out.println("    " + currTask.toString());
            break;
        case "unmark":
            System.out.println("  OK, continue working on: ");
            System.out.println("    " + currTask.toString());
            break;
        case "delete":
            System.out.println("  I've removed this task:");
            System.out.println("    " + currTask.toString());
            System.out.println("  Now you have "+ String.valueOf(this.myTaskList.countTasks()) +
                    " tasks in the list!");
            break;
        case "deadline":
        case "event":
        case "todo":
            printNewTask(currTask);
            break;
        }
    }
    public void printMatchingTasks(ArrayList<Task> matchingTasks) {
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println("You have " + String.valueOf(matchingTasks.size()) + " matching tasks");
            System.out.println("  " + matchingTasks.get(i));
        }
    }
}
