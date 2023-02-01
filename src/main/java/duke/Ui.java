package duke;

import duke.Task;

import java.util.ArrayList;

public class Ui {
    Ui() {}

    /**
     * Output message of marking
     * @param t -> task mark as done
     */
    public void showMark(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    /**
     * Output message of Unmarking
     * @param t -> task mark as not done
     */
    public void showMarkNotDone(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
    }

    /**
     * Greets to user
     */
    public void sayHello() {
        System.out.println("Hello! I'm Luminus\nWhat can I do for you?");
    }

    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * show list of task to user
     * @param list -> list of tasks
     */
    public void showList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1));
        }
    }

    /**
     * When a task is added to list, following will be output
     * 1. task(with description) is added (to the end of list)
     * 2. print current size of list
     * @param list -> list of tasks
     */
    public void showAddTask(ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(list.get(list.size() - 1));
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * When a task is deleted from list, following will be output
     * 1. which task is deleted
     * 2. After deletion, how many tasks are left
     * @param temp -> the deleted task
     * @param list -> list after deletion
     */
    public void showDelete(Task temp, ArrayList<Task> list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(temp);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Output the tasks which matches keywords
     * @param matches
     */
    public void showFind(ArrayList<Task> matches) {
        if (matches.size() == 0) {
            System.out.println("Sorry, I can't find any tasks that matches your keywords.");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i++) {
            Task temp = matches.get(i);
            System.out.println((i + 1) + ". " + temp);
        }
    }

}
