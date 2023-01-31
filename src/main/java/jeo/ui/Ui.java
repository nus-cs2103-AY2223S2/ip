package jeo.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jeo.database.TaskList;
import jeo.task.Deadline;
import jeo.task.Event;
import jeo.task.Task;

/**
 * Represents the Ui for dealing with user interactions.
 * @author Goh Jun How
 * @version 0.1
 */
public class Ui {
    protected static final String BOT_NAME = "JeoBot";
    protected static final String DATE_TO_PRINT = "d MMM yyyy";

    /**
     * Prints a divider for the greeting message.
     */
    public void showStartingDivider() {
        System.out.println("-----------------------");
    }

    /**
     * Prints a divider for command response messages.
     */
    public void showBodyDivider() {
        System.out.println("________________________________________________________________"
                + "_________________________");
    }

    /**
     * Prints the greeting message.
     */
    public void showGreetingMessage() {
        showStartingDivider();
        System.out.println("Greetings from " + BOT_NAME + "!\n" + "How may I help you?");
        showStartingDivider();
    }

    /**
     * Prints the exit message.
     */
    public void showExitMessage() {
        System.out.println("Thank you for using " + BOT_NAME + ". Hope to see you again soon!");
    }

    /**
     * Prints the error message for an invalid command.
     */
    public void showInvalidCommand() {
        System.out.println("[Error] Sorry, I don't understand what you're saying :(");
    }

    /**
     * Prints the error message for an invalid task number.
     */
    public void showIndexingError() {
        System.out.println("[Error] Task number cannot be negative, zero, or exceed the total number of tasks.");
    }

    /**
     * Prints the error message when unable to convert a string to a date-time object.
     */
    public void showDateTimeParsingError() {
        System.out.println("[Error] Unable to parse date-time. "
                + "Please input date in the format: \"yyyy-MM-dd HH:mm\".");
    }

    /**
     * Prints the custom error message.
     * @param msg String representing the custom error message.
     */
    public void showJeoErrorMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints the error message when unable to load tasks.
     */
    public void showLoadingError() {
        System.out.println("Error encountered when loading tasks :(\n");
        System.out.println("Ignore this message if it is your first time using my service :>");
    }

    /**
     * Prints the error message when unable to save tasks.
     */
    public void showSavingError() {
        System.out.println("Error encountered when saving tasks :(");
    }

    /**
     * Prints the message when a task is added.
     * @param task Task which is just added.
     * @param taskListSize An integer representing the current number of tasks.
     */
    public void showTaskAdded(Task task, int taskListSize) {
        String statement = "Got it! I've added this task:\n" + task + "\nNow you have ";
        System.out.println(statement + taskListSize + " task(s) in the list.");
    }

    /**
     * Prints the message when a task is deleted.
     * @param task Task which is just deleted.
     * @param taskListSize An integer representing the remaining number of tasks.
     */
    public void showTaskDeleted(Task task, int taskListSize) {
        String statement = "Got it! I've removed this task:\n" + task + "\nNow you have ";
        System.out.println(statement + taskListSize + " task(s) in the list.");
    }

    /**
     * Prints the message when a task is marked.
     * @param task Task which is just marked.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Good job! I've marked this task as done:\n" + task);
    }

    /**
     * Prints the message when a task is unmarked.
     * @param task Task which is just unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("Okay, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Prints the current list of tasks.
     * @param list TaskList representing the list of all tasks.
     */
    public void showAllTasks(TaskList list) {
        System.out.println("Here are all the tasks in your list:");
        for (int i = 0; i < list.getNumberOfTasks(); i++) {
            StringBuilder sb = new StringBuilder()
                    .append(i + 1)
                    .append(".")
                    .append(list.getTaskAtIndex(i));
            System.out.println(sb);
        }
    }

    /**
     * Prints the tasks that are due on the specified date.
     * @param byDate LocalDate representing the due date.
     * @param list TaskList representing the list of all tasks.
     */
    public void showTasksDue(LocalDate byDate, TaskList list) {
        DateTimeFormatter formatterPrint = DateTimeFormatter.ofPattern(DATE_TO_PRINT);
        String formattedDueDate = byDate.format(formatterPrint);
        int j = 1;
        System.out.println("Here are the task(s) due on " + formattedDueDate + " :");
        for (int i = 0; i < list.getNumberOfTasks(); i++) {
            Task currTask = list.getTaskAtIndex(i);
            StringBuilder sb = new StringBuilder()
                    .append(j)
                    .append(".");
            if (currTask instanceof Deadline) {
                if (((Deadline) currTask).getDateTimeBy().toLocalDate().compareTo(byDate) == 0) {
                    sb.append(currTask);
                    System.out.println(sb);
                    j++;
                }
            } else if (currTask instanceof Event) {
                if (((Event) currTask).getDateTimeTo().toLocalDate().compareTo(byDate) == 0) {
                    sb.append(currTask);
                    System.out.println(sb);
                    j++;
                }
            }
        }
    }

    /**
     * Prints the tasks that match the specified keyword.
     * @param keyword String representing the keyword.
     * @param list TaskList representing the list of all tasks.
     */
    public void showTasksWithKeyword(String keyword, TaskList list) {
        System.out.println("Here are the matching tasks in your list:");
        int j = 1;
        for (Task task: list.getTaskList()) {
            if (task.toString().contains(keyword)) {
                StringBuilder sb = new StringBuilder()
                        .append(j)
                        .append(".")
                        .append(task);
                System.out.println(sb);
                j++;
            }
        }
    }
}
