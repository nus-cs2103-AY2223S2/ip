package duke;
import java.util.ArrayList;

import Task.Task;
/**
 * Class that display messages (except error messages) to user
 */
public class Ui {
    public Ui() {}

    /**
     * Output message of marking
     * @param t -> task mark as done
     */
    public String showMark(Task t) {
        return "Nice! I've marked this task as done:\n" + t + "\n";
    }

    /**
     * Output message of Unmarking
     * @param t -> task mark as not done
     */
    public String showMarkNotDone(Task t) {
        return "OK, I've marked this task as not done yet:\n" + t + "\n";
    }

    /**
     * Greets to user
     */
    public String sayHello() {
        return "Hello! I'm Duke\nWhat can I do for you?\n";
    }

    public String sayBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * show list of task to user
     * @param list -> list of tasks
     */
    public String showList(ArrayList<Task> list) {
        if (list.size() == 0) {
            return "Excuse me, your list is still empty lei";
        }
        assert(list.size() > 0);
        StringBuilder str = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 1; i <= list.size(); i++) {
            str.append(i + ". " + list.get(i - 1) + "\n");
        }
        return str.toString();
    }

    /**
     * When a task is added to list, following will be output
     * 1. task(with description) is added (to the end of list)
     * 2. print current size of list
     * @param list -> list of tasks
     */
    public String showAddTask(ArrayList<Task> list) {
        StringBuilder str = new StringBuilder("Got it. I've added this task:\n");
        str.append(list.get(list.size() - 1) + "\n");
        str.append("Now you have " + list.size() + " tasks in the list.\n");
        return str.toString();
    }

    /**
     * When a task is deleted from list, following will be output
     * 1. which task is deleted
     * 2. After deletion, how many tasks are left
     * @param temp -> the deleted task
     * @param list -> list after deletion
     */
    public String showDelete(Task temp, ArrayList<Task> list) {
        StringBuilder str = new StringBuilder("Noted. I've removed this task:\n");
        str.append(temp + "\n");
        str.append("Now you have " + list.size() + " tasks in the list.\n");
        return str.toString();
    }

    /**
     * Output the tasks which matches keywords
     * @param matches
     */
    public String showFind(ArrayList<Task> matches) {
        if (matches.size() == 0) {
            return "Sorry, I can't find any tasks that matches your keywords.\n";
        }
        StringBuilder str = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            Task temp = matches.get(i);
            str.append((i + 1) + ". " + temp + "\n");
        }
        return str.toString();
    }

}
