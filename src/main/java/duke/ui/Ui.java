package duke.ui;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Task;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    public String showLine() {
        return "________________________________________________________________\n";
    }

    public String readCommand() {
//            throws DukeException {
        Scanner sc = new Scanner(System.in);
        String command = "";
//        try {
            command = sc.nextLine();
//        } catch (NoSuchElementException e) {
//            throw new DukeException("No such element.");
//        }
        return command;
    }

    /**
     * Prints out the greeting for Fake Duke.
     */
    public void showWelcome() {
        String logo = "  _____     _       _  __  U _____ u      ____     _   _    _  __  U _____ u \n"
                + " |\" ___|U  /\"\\  u  |\"|/ /  \\| ___\"|/     |  _\"\\ U |\"|u| |  |\"|/ /  \\| ___\"|/ \n"
                + "U| |_  u \\/ _ \\/   | ' /    |  _|\"      /| | | | \\| |\\| |  | ' /    |  _|\"   \n"
                + "\\|  _|/  / ___ \\ U/| . \\\\u  | |___      U| |_| |\\ | |_| |U/| . \\\\u  | |___   \n"
                + " |_|    /_/   \\_\\  |_|\\_\\   |_____|      |____/ u<<\\___/   |_|\\_\\   |_____|  \n"
                + " )(\\\\,-  \\\\    >>,-,>> \\\\,-.<<   >>       |||_  (__) )(  ,-,>> \\\\,-.<<   >>  \n"
                + "(__)(_/ (__)  (__)\\.)   (_/(__) (__)     (__)_)     (__)  \\.)   (_/(__) (__) \n";
        System.out.println(this.showLine()
                + "Hello!~ I'm the one and only\n"
                + logo
                + "What can I do for you?\n"
                + this.showLine());
    }

    public void showAdd(Task task, TaskList taskList) {
        System.out.println(this.showLine()
                + "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have "
                + taskList.getSize()
                + " tasks in the list.\n"
                + this.showLine());
    }

    public void showDelete(String taskString, TaskList taskList) {
        System.out.println(this.showLine()
                + "Noted. I've removed this task:\n"
                + taskString
                + "\nNow you have "
                + taskList.getSize()
                + " tasks in the list.\n"
                + this.showLine());
    }

    public void showMark(String taskString, TaskList taskList) {
        System.out.println(this.showLine()
                + "Nice! I've marked this task as done:\n"
                + taskString
                + "\n"
                + this.showLine());
    }

    public void showUnmark(String taskString, TaskList taskList) {
        System.out.println(this.showLine()
                + "OK, I've marked this task as not done yet:\n"
                + taskString
                + "\n"
                + this.showLine());
    }

    /**
     * Displays the list of tasks.
     */
    public void showList(TaskList taskList) throws DukeException {
        System.out.println(this.showLine()
                + "Here are the tasks in your list:");
        for (int i = 1; i <= taskList.getSize(); i++) {
            System.out.println(i + "." + taskList.getTask(i - 1).toString());
        }
        System.out.println(this.showLine());
    }

    /**
     * Prints out an exit message and exits the program.
     */
    public void showExit() {
        System.out.println(this.showLine()
                + "Hope I have been useful to you.\n"
                + "See you again soon. Bye!~\n"
                + this.showLine());
    }

    public void showError(String error) {
        System.out.println(this.showLine() + error + this.showLine());
    }
}
