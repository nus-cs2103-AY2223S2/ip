package ui;

import storage.TaskList;
import task.Task;

public class Ui {
    public Ui() {};

    public void printInvalidCommandMessage() {
        System.out.println("Sorry, I don't know what that means! :'( \n");
    }
    public void printWelcomeMessage() {
        System.out.println("Hello!\n" + "What can I do for you?\n");
    }
    public void showLine() {
        System.out.println("----------------------------------");
    }

    public void printListMessage() {
        System.out.println("Here are the tasks in your list: \n");
    }

    public void printAddMessage(Task newTask, TaskList list) {
        System.out.println("Got it. I've added this task:\n" + newTask + "\nNow you have "
                + list.size() + " tasks in the list.");
    }

    public void printMarkMessage(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" + task);
    }

    public void printUnmarkMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n" + task);
    }

    public void printDeleteMessage(Task removed, TaskList list) {
        System.out.println("Noted. I've removed this task: \n" + removed + "\nNow you have "
                + (list.size() - 1) + " tasks in the list.");
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}