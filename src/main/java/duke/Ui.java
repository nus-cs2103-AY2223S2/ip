package duke;
import java.util.Scanner;
import duke.tasks.Task;
public class Ui {
    public Ui () {
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello i'm\n" + logo);
        System.out.println("What can i do for you?");
    }

    public void showLine() {
        System.out.println("____________________________________________");
    }

    public String readCommand() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Unable to load tasks from storage");
    }

    public void printAddedTask(Task t, int number_of_tasks) {
        System.out.print("Got it. I've added this task:\n" + t.toString() + "\n" + "Now you have " + number_of_tasks + " tasks in the list.\n");
    }

    public void printMarked(Task t) { 
        System.out.println( "Nice! I've marked this task as done:\n" + t.toString());
    }

    public void printUnmarked(Task t) { 
        System.out.println( "OK, I've marked this task as not done yet:\n" + t.toString());
    }

    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again!");
    }
    public void printRemovedMessage(Task t, int number_of_tasks) {
        System.out.print("Noted. I've removed this task:\n" + 
        t.toString() + "\n" + "Now you have " + number_of_tasks + " tasks in the list.\n");
    }
    public void printFindTask() {
        System.out.println("Here are the matching tasks in your list:\n");
    }

    public void showError(String error) {
        System.out.println(error);
    }
    public void Message() {

    }
}
