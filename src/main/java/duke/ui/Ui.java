package duke.ui;

import duke.storage.TaskList;
import duke.task.Task;

public class Ui {

    public String printInvalidCommandMessage() {
        return ("Sorry, I don't know what that means! :'( \n");
    }
    public String printWelcomeMessage() {
        return ("Hello!\n" + "What can I do for you?\n");
    }
    public String showLine() {
        return ("----------------------------------");
    }

    public String printFindMessage() {
        return ("Here are the matching tasks in your list:\n");
    }

    public String printListMessage() {
        return ("Here are the tasks in your list: \n");
    }

    public String printAddMessage(Task newTask, TaskList list) {
        return ("Got it. I've added this task:\n" + newTask + "\nNow you have "
                + list.size() + " tasks in the list.");
    }

    public String printMarkMessage(Task task) {
        return ("Nice! I've marked this task as done: \n" + task);
    }

    public String printUnmarkMessage(Task task) {
        return ("OK, I've marked this task as not done yet: \n" + task);
    }

    public String printDeleteMessage(Task removed, TaskList list) {
        return ("Noted. I've removed this task: \n" + removed + "\nNow you have "
                + (list.size() - 1) + " tasks in the list.");
    }

    public String printByeMessage() {
        return ("Bye. Hope to see you again soon!");
    }

    public String printDuplicateTaskMessage() {
        return ("Oops, this task already exist!");
    }

    public String printOutOfBoundsMessage(TaskList list) {
        return ("There are " + list.size() + " tasks in the list, please choose " +
                "a number from 1 to " + list.size() + "!");
    }
}