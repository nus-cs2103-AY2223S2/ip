package james;

import james.task.Task;
import james.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private Scanner scan = new Scanner(System.in);

    public void welcome() {
        System.out.println("Hello! I'm James.");
        System.out.println("How can I help you today?");
    }
    public void exit() {
        System.out.println("Bye. Hope to see you soon!");
        scan.close();
    }

    public void addTask(Task task, int taskSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskSize + " tasks in the list.");
    }

    public void markTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void unmarkTask(Task task) {
        System.out.println("Nice! I've marked this task as not done:");
        System.out.println(task);
    }


    public void eraseTask(Task task, int taskSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskSize + " tasks in the list.");
    }

    public void printTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task t = taskList.getTask(i);
            System.out.println("" + (i + 1) + "." + t);
        }
    }

    public void printError(JamesException e) {
        System.out.println(e.getMessage());
    }


    public String readCommand() {
        return scan.nextLine();
    }

    public void printFoundTasks(ArrayList<Task> foundTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            Task t = foundTasks.get(i);
            System.out.println((i + 1) + "." + t);
        }
    }

}
