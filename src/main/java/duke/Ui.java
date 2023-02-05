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
    private ArrayList<String> printNewTask(Task t) {
        ArrayList<String> temp = new ArrayList<>();

        if (t instanceof Event) {
            temp.add("  new event added!");
        } else if (t instanceof Deadline) {
            temp.add("  new deadline added!");
        } else if (t instanceof ToDo) {
            temp.add("  new todo added!");
        }
        temp.add("    " + t.toString());
        temp.add("  Now you have " + String.valueOf(this.myTaskList.countTasks()) +
                " tasks in the list!");
        return temp;
    }

    /**
     * Prints a reply to standard output when the user does not enter a task.
     * @param inputCommand What the user inputs
     */
    public ArrayList<String> printReply(String inputCommand) {
        ArrayList<String> temp = new ArrayList<>();
        switch (inputCommand) {
        case "list":
            for (int i = 0; i < myTaskList.countTasks(); i++) {
                temp.add("  " + String.valueOf(i + 1) + ". " + myTaskList.getTaskAtIndex(i));
            }
            break;
        case "bye":
            temp.add("  See you again");
            break;
        default:
            temp.add("  this is not a task, contact admin");
            break;
        }
        return temp;
    }
    /**
     * Prints a reply to standard output given a command to add, mark or delete a task.
     * @param inputCommand What the user inputs
     */
    public ArrayList<String> printReply(String inputCommand, Task currTask) throws EmptyDescriptionException {
        ArrayList<String> temp = new ArrayList<>();
        switch (inputCommand) {
        case "mark":
            temp.add("  You are done with: ");
            temp.add("    " + currTask.toString());
            break;
        case "unmark":
            temp.add("  OK, continue working on: ");
            temp.add("    " + currTask.toString());
            break;
        case "delete":
            temp.add("  I've removed this task:");
            temp.add("    " + currTask.toString());
            temp.add("  Now you have "+ String.valueOf(this.myTaskList.countTasks()) +
                    " tasks in the list!");
            break;
        case "deadline":
        case "event":
        case "todo":
            temp = printNewTask(currTask);
            break;
        }
        return temp;
    }

    public ArrayList<String> printMatchingTasks(ArrayList<Task> matchingTasks) {
        ArrayList<String> temp = new ArrayList<>();
        temp.add("You have " + String.valueOf(matchingTasks.size()) + " matching tasks");
        for (int i = 0; i < matchingTasks.size(); i++) {
            temp.add("  " + matchingTasks.get(i));
        }
        return temp;
    }
}
