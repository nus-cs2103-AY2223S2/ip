import java.time.LocalDate;
import java.util.Scanner;

public class Ui { // handles I/O things
    Scanner myScanner;
    TaskList myTaskList;

    public Ui() {
        this.myScanner = new Scanner(System.in);
        this.myTaskList = new TaskList();
    }
    public String getNextTask() {
        return this.myScanner.nextLine();
    }
    private static void printNewTask(Task t) {
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
        System.out.println("  Now you have " + String.valueOf(Duke.countTasks()) +
                " tasks in the list!");
    }
    public void printSomething(String inputCommand, Task currTask) throws EmptyDescriptionException {
        switch (inputCommand) {
            case "bye":
                System.out.println("  Bye. Hope to see you soon again!");
            case "mark":
                System.out.println("  You are done with: ");
                System.out.println("    " + currTask.toString());
            case "unmark":
                System.out.println("  OK, continue working on: ");
                System.out.println("    " + currTask.toString());
            case "delete":
                System.out.println("  I've removed this task:");
                System.out.println("    " + currTask.toString());
                System.out.println("  Now you have "+ String.valueOf(Duke.countTasks()) +
                        " tasks in the list!");
            case "deadline":
            case "event":
            case "todo":
                printNewTask(currTask);
            case "list":
                for (int i = 0; i < myTaskList.countTasks(); i++) {
                    System.out.println("  " + String.valueOf(i + 1) + ". " + myTaskList.getTaskAtIndex(i));
                }
            default:
                System.out.println("  this is not a task, contact admin");
        }
    }
}
