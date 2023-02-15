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
     * Returns the greeting message.
     * @return the greeting message.
     */
    public String greetingMessage() {
        return "Greetings from " + BOT_NAME + "!\n" + "How may I help you?";
    }

    /**
     * Returns the exit message.
     * @return the exit message.
     */
    public String exitMessage() {
        return "Thank you for using " + BOT_NAME + ". Hope to see you again soon!";
    }

    /**
     * Returns the error message for an invalid command.
     * @return the error message for an invalid command.
     */
    public String invalidCommandMessage() {
        return "[Error] Sorry, I don't understand what you're saying :(";
    }

    /**
     * Returns the error message for an invalid task number.
     * @return the error message for an invalid task number.
     */
    public String indexingErrorMessage() {
        return "[Error] Task number cannot be negative, zero, or exceed the total number of tasks.";
    }

    /**
     * Returns the error message when unable to convert a string to a date-time object.
     * @return the error message when unable to convert a string to a date-time object.
     */
    public String dateTimeParsingErrorMessage() {
        return "[Error] Unable to parse date-time. "
                + "Please input date in the format: \"yyyy-MM-dd HH:mm\".";
    }

    /**
     * Returns the custom error message.
     * @param message String representing the custom error message.
     * @return the custom error message.
     */
    public String jeoErrorMessage(String message) {
        return message;
    }

    /**
     * Prints the error message when unable to load tasks.
     */
    public void showLoadingErrorMessage() {
        System.out.println("Error encountered when loading tasks :(\n"
                + "Ignore this message if it is your first time using my service :>");
    }

    /**
     * Returns the error message when unable to save tasks.
     * @return the error message when unable to save tasks.
     */
    public String savingErrorMessage() {
        return "Error encountered when saving tasks :(";
    }

    /**
     * Returns the message when a task is added.
     * @param task Task which is just added.
     * @param taskListSize An integer representing the current number of tasks.
     * @return the message when a task is added.
     */
    public String taskAddedMessage(Task task, int taskListSize) {
        String statement = "Got it! I've added this task:\n" + task + "\nNow you have ";
        return statement + taskListSize + " task(s) in the list.";
    }

    /**
     * Returns the message when a task is deleted.
     * @param task Task which is just deleted.
     * @param taskListSize An integer representing the remaining number of tasks.
     * @return the message when a task is deleted.
     */
    public String taskDeletedMessage(Task task, int taskListSize) {
        String statement = "Got it! I've removed this task:\n" + task + "\nNow you have ";
        return statement + taskListSize + " task(s) in the list.";
    }

    /**
     * Returns the message when a task is marked.
     * @param task Task which is just marked.
     * @return the message when a task is marked.
     */
    public String taskMarkedMessage(Task task) {
        return "Good job! I've marked this task as done:\n" + task;
    }

    /**
     * Returns the message when a task is unmarked.
     * @param task Task which is just unmarked.
     * @return the message when a task is unmarked.
     */
    public String taskUnmarkedMessage(Task task) {
        return "Okay, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns the string representation of the current list of tasks.
     * @param list TaskList representing the list of all tasks.
     * @return the string representation of the current list of tasks.
     */
    public String showAllTasks(TaskList list) {
        StringBuilder sb = new StringBuilder("Here are all the tasks in your list:");
        for (int i = 0; i < list.getNumberOfTasks(); i++) {
            sb.append("\n").append(i + 1).append(".").append(list.getTaskAtIndex(i));
        }
        return sb.toString();
    }

    /**
     * Returns the string representation of tasks that are due on the specified date.
     * @param byDate LocalDate representing the due date.
     * @param list TaskList representing the list of all tasks.
     * @return the string representation of tasks that are due on the specified date.
     */
    public String showTasksDue(LocalDate byDate, TaskList list) {
        DateTimeFormatter formatterPrint = DateTimeFormatter.ofPattern(DATE_TO_PRINT);
        String formattedDueDate = byDate.format(formatterPrint);
        int j = 1;
        StringBuilder sb = new StringBuilder("Here are the task(s) due on " + formattedDueDate + " :");
        for (int i = 0; i < list.getNumberOfTasks(); i++) {
            Task currTask = list.getTaskAtIndex(i);
            boolean deadlineDueDateMatch = currTask instanceof Deadline
                    && ((Deadline) currTask).getDateTimeBy().toLocalDate().compareTo(byDate) == 0;
            boolean eventDueDateMatch = currTask instanceof Event
                    && ((Event) currTask).getDateTimeTo().toLocalDate().compareTo(byDate) == 0;
            if (deadlineDueDateMatch || eventDueDateMatch) {
                sb.append("\n").append(j).append(".").append(currTask);
                j++;
            }
        }
        return sb.toString();
    }

    /**
     * Returns the string representation of tasks that match the specified keyword.
     * @param keyword String representing the keyword.
     * @param list TaskList representing the list of all tasks.
     * @return the string representation of tasks that match the specified keyword.
     */
    public String showTasksWithKeyword(String keyword, TaskList list) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
        int j = 1;
        for (Task task: list.getTaskList()) {
            if (task.toString().contains(keyword)) {
                sb.append("\n").append(j).append(".").append(task);
                j++;
            }
        }
        return sb.toString();
    }
}
