package duke;
import java.util.Scanner;
import duke.tasks.Task;
public class Ui {
    public Ui () {
    }

    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello i'm\n" + logo + "What can i do for you?";
    }

    public void showLine() {
        System.out.println("____________________________________________");
    }

    public String readCommand() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public String showLoadingError() {
        return "Unable to load tasks from storage";
    }

    public String printAddedTask(Task t, int number_of_tasks) {
        return "Got it. I've added this task:\n" + t.toString() + "\n" + "Now you have " + number_of_tasks + " tasks in the list.\n";
    }

    public String printMarked(Task t) {
        return "Nice! I've marked this task as done:\n" + t.toString();
    }

    public String printUnmarked(Task t) {
        return "OK, I've marked this task as not done yet:\n" + t.toString();
    }

    public String printExitMessage() {
        return "Bye. Hope to see you again!";
    }
    public String printRemovedMessage(Task t, int number_of_tasks) {
        return "Noted. I've removed this task:\n" +
        t.toString() + "\n" + "Now you have " + number_of_tasks + " tasks in the list.\n";
    }
    public String printFindTask() {
        return "Here are the matching tasks in your list:\n";
    }

    public String showError(String error) {
        return error;
    }
    public String printErrorMessage() {
        return "oops, something went wrong :(";
    }
    public String printInvalidCommandError() {
        return "OOPS!!! I'm sorry, but I don't know what that means :(((((";
    }
    public void Message() {

    }
}
