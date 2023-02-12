package duke;

import task.Task;
import task.TaskList;

import java.util.List;

/**
 * Handles the UI elements of chat-bot Duke.
 */
public class Phrase {

    /** Name of chat-bot */
    public static String botName = "DUKE";

    /**
     * Intro of chat-bot.
     *
     * @return Intro string.
     */
    public String getIntro() {
        return "Hello! I'm\n" + Phrase.botName + "\nWhat can I do for you?";
    }

    /**
     * Outro of chat-bot.
     *
     * @return Outro string.
     */
    public String getOutro() {
        return "Good Riddance!";
    }

    /**
     * Get all tasks currently in the list.
     *
     * @param taskList List of tasks.
     * @return All tasks in the list string.
     */
    public String getList(TaskList taskList) {
        return taskList.toString();
    }

    public String getListWithAttitude(TaskList taskList) {
        return "\nTsk... here is your list:\n" + this.getList(taskList);
    }







    /**
     * Print list with duke personality.
     *
     * @param taskList List of task.
     */
    public void printListWithAttitude(TaskList taskList) {
        System.out.println("\nTsk... here is your list:\n");
     //   this.printList(taskList);
    }

    /**
     * Print too much input message.
     */
    public void printIndexOutOfBoundMessage() {
        System.out.println("Are you blind? That's not an option!!");
    }

    /**
     * Print confirmation message for add task.
     *
     * @param t Task added.
     */
    public void printAddTaskSuccessfulMessage(Task t) {
        System.out.println("\nOkay... I've added this task:\n  " + t);
    }

    /**
     * Prints confirmation message for unmarking task.
     *
     * @param t Task unmarked.
     */
    public void printUnMarkMessage(Task t) {
        System.out.println("\nOK, I've marked this task as not done yet:\n  " + t + "\n");

    }

    /**
     * Prints confirmation message for marking task.
     *
     * @param t Task marked.
     */
    public void printMarkMessage(Task t) {
        System.out.println("\nOK, I've marked this task as done yet:\n  " + t + "\n");

    }

    /**
     * Prints confirmation message for deleting task.
     *
     * @param t Task deleted.
     */
    public void printDeleteSuccessfulMessage(Task t) {
        System.out.println("\nNoted. I've purged this task:\n  " + t);
    }

    /**
     * Prints empty details message.
     *
     * @param taskType Task type.
     */
    public void printEmptyDetailsMessage(String taskType) {
        System.out.println("EXCUSE ME!!!, '" + taskType + "'  + details cannot be empty");
    }

    /**
     * Prints proper format for deadline.
     */
    public void printDeadlineFormat() {
        System.out.println("EXCUSE ME!!!, please follow the format\ndeadline <detail> /by dd/mm/yyyy");
    }

    /**
     * Prints proper format for event.
     */
    public void printEventFormat() {
        System.out.println("EXCUSE ME!!!, please follow the format\nevent <details> /from dd/mm/yyyy /to dd/mm/yyyy");

    }

    /**
     * Prints proper format for date.
     */
    public void printDateFormat() {
        System.out.println("EXCUSE ME!!!, please use the correct date format\n dd/mm/yyyy");
    }

    /**
     * Prints total task stored.
     *
     * @param storedInputs List of task.
     */
    public void printTotalTask(List<Task> storedInputs) {
        System.out.println("You currently have " + storedInputs.size()  + " tasks in the list.\n");
    }

    /**
     * Prints found task message.
     *
     * @param allTaskFound String to print.
     */
    public void printFoundTasks(String allTaskFound) {
        if (allTaskFound.isBlank()) {
            System.out.println("DOES NOT EXIST");
        } else {
            System.out.println("\nHere you go:\n" + allTaskFound);
        }
    }

}
