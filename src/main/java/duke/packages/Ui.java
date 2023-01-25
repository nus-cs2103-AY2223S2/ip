package duke.packages;
import java.util.Scanner;

public class Ui { // handles I/O things
    Scanner myScanner;
    TaskList myTaskList;

    public Ui(TaskList myTaskList) {
        this.myScanner = new Scanner(System.in);
        this.myTaskList = myTaskList;
    }
    public String getNextTask() {
        return this.myScanner.nextLine();
    }
    public void greetUser() {
        System.out.println("  Insert ingenious greeting here");
    }
    private void printNewTask(Task t) {
        if (t instanceof Event) {
            System.out.println("  new event added!");
        }
        else if (t instanceof Deadline) {
            System.out.println("  new deadline added!");
        }
        else if (t instanceof ToDo) {
            System.out.println("  new todo added!");
        }
        System.out.println("    " + t.toString());
        System.out.println("  Now you have " + String.valueOf(this.myTaskList.countTasks()) +
                " tasks in the list!");
    }
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
}
