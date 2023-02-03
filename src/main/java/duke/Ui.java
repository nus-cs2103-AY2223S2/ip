package duke;

import java.util.List;

/**
 * Handles the UI elements of chat-bot Duke.
 */
public class Ui {

    private String logo() {
        return " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }

    public void printIntro() {
        System.out.println("Hello! I'm\n" + logo() + "\nWhat can I do for you?");
    }

    public void printOutro() {
        System.out.println("Good Riddance!");
    }

    public void printList(TaskList taskList) {
        System.out.println(taskList);
    }

    public void printListWithAttitude(TaskList taskList) {
        System.out.println("\nTsk... here is your list:\n");
        this.printList(taskList);
    }

    public void printIndexOutOfBoundMessage() {
        System.out.println("Are you blind? That's not an option!!");
    }

    public void printAddTaskSuccessfulMessage(Task t) {
        System.out.println("\nOkay... I've added this task:\n  " + t);
    }

    public void printUnMarkMessage(Task t) {
        System.out.println("\nOK, I've marked this task as not done yet:\n  " + t + "\n");

    }

    public void printMarkMessage(Task t) {
        System.out.println("\nOK, I've marked this task as done yet:\n  " + t + "\n");

    }

    public void printDeleteSuccessfulMessage(Task t) {
        System.out.println("\nNoted. I've purged this task:\n  " + t);
    }

    public void printEmptyDetailsMessage(String taskType) {
        System.out.println("EXCUSE ME!!!, '" + taskType + "'  + details cannot be empty");
    }

    public void printDeadlineFormat() {
        System.out.println("EXCUSE ME!!!, please follow the format\ndeadline <detail> /by dd/mm/yyyy");
    }

    public void printEventFormat() {
        System.out.println("EXCUSE ME!!!, please follow the format\nevent <details> /from dd/mm/yyyy /to dd/mm/yyyy");

    }

    public void printDateFormat() {
        System.out.println("EXCUSE ME!!!, please use the correct date format\n dd/mm/yyyy");
    }

    public void printTotalTask(List<Task> storedInputs) {
        System.out.println("You currently have " + storedInputs.size()  + " tasks in the list.\n");
    }
}
