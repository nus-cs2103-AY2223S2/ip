package duke.ui;

import java.util.Scanner;

import duke.tasklist.TaskList;
import duke.tasktypes.Task;

public class Ui {

    public Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void close() {
        sc.close();
    }

    public void showLine() {
        System.out.println("--------------------------------");
    }

    public void greet() {
        String greeting = "What's up! XyDuke here!\nHow can I be of assistance?";
        System.out.println(greeting);
    }

    public void goodbye() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
    }

    public String nextInput() {
        return sc.nextLine();
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void directoryCreate() {
        System.out.println("Data folder created!");
    }

    public void fileCreate() {
        System.out.println("Duke data file: duke.txt created!");
    }

    public void uploading() {
        System.out.println("Updating your data. Please wait..");
    }

    public void saved() {
        System.out.println("All changes saved successfully!");
    }

    public void taskAdd(Task task, int numTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    public void taskDelete(Task task, int numTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    public void markTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void markTaskUndone(Task task) {
        System.out.println("OK, I've marked this task as undone:\n" + task);
    }

    public void noMatchingTask() {
        System.out.println("Sorry! There are no matching tasks in your current list!");
    }

    public void printTasks(TaskList tasks) {
        tasks.printTasks();
    }

}
