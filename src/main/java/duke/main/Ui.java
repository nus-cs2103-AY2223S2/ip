package duke.main;

import duke.main.DukeException;
import duke.main.Tasklist;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public void printGreeting() {
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
    }
    public void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public void printTaskNum(Tasklist tasklist) {
        System.out.println("Now you have " + tasklist.getTasks().size() + " tasks in the list.");
    }
    public  void printAddTaskMessage(Task task, Tasklist tasklist) {
        printLine();
        System.out.println("Got it. I've added this task:\n" + task);
        printTaskNum(tasklist);
        printLine();

    }
    public  void printDeleteTaskMessage(Task task, Tasklist tasklist) {
        printLine();
        System.out.println(" Noted. I've removed this task:\n" + task);
        printTaskNum(tasklist);
        printLine();
    }
    public void printTasks(Tasklist tasklist) {
        printLine();
        ArrayList<Task> tasks = tasklist.getTasks();
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1).toString() );
        }
        printLine();
    }
    public  void printMarkTaskMessage(Task task) {
        printLine();
        System.out.println("Nice! This task is marked as done:\n" + task.toString());
        printLine();
    }
    public  void printUnmarkTaskMessage(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
        printLine();
    }
    public  void printLine() {
        System.out.println("____________________________________________________________");
    }
    public void printDukeException(DukeException de) {
        printLine();
        System.out.println(de.getMessage());
        printLine();
    }
    public void printIOException(IOException ie) {
        printLine();
        System.out.println(" ☹ OOPS!!! IOException occured");
        printLine();
    }
    public String readCommand() {
        return this.sc.nextLine();
    }
    public void closeScanner() {
        this.sc.close();
    }
}
