package utilities;

import collections.TaskList;
import exceptions.SundayException;
import task.Task;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * The Ui class is a user interface for the Sunday task manager.
 * It contains methods for returning the appropriate responses according to user inputs.
 *
 * @author Tan Yan-Hao Joshua
 */
public class Ui {

    private static final PrintStream dummyStream = new PrintStream(new OutputStream() {
        @Override
        public void write(int b) {
            // Empty to hide prints while reading from file
        }
    });

    private static final PrintStream defaultStream = System.out;

    /**
     * Sets the output stream to a dummy stream.
     */
    public static void setDummyStream() {
        System.setOut(dummyStream);
    }

    /**
     * Sets the output stream back to the default stream.
     */
    public static void setDefaultStream() {
        System.setOut(defaultStream);
    }

    /**
     * Returns the appropriate welcome message to the user, depending on whether the user is new or not.
     *
     * @param isNewUser True if the user is new, otherwise False.
     * @return The appropriate welcome message.
     */
    public static String getWelcomeMessage(boolean isNewUser) {
        StringBuilder sb = new StringBuilder();
        sb.append("Hi! I'm Sunday, pleasure to meet you!\n");
        sb.append("How can I help?\n");
        if (isNewUser) {
            sb.append("It appears we haven't met!\n");
            sb.append("Start typing away your tasks and I'll note them down accordingly :)\n");
        } else {
            sb.append("It appears we've met! I've restored your task list from our last session.\n");
        }
        return sb.toString();
    }

    /**
     * Returns a task list as a message to the user.
     *
     * @param taskList The task list to be viewed.
     * @return The task list as a message.
     */
    public static String getTaskListMessage(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here's everything I've noted down for you:\n");

        String[] strArr = taskList.toString().split("\n");
        for (int i = 0; i < strArr.length; i++) {
            sb.append(i + 1).append(". ").append(strArr[i]).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a message to the user indicating that the task list is empty.
     *
     * @return The message indicating that the task list is empty.
     */
    public static String getEmptyTaskListMessage() {
        return "Your list is currently empty.\n" +
                "Tell me what to note down and I'll remember it accordingly!\n";
    }

    /**
     * Returns a message to the user indicating that a task has been added to the task list.
     *
     * @param task The task that has been added.
     * @param uncompletedSize The number of uncompleted tasks in the task list.
     * @return The message indicating that a task has been added to the task list.
     */
    public static String getAddedTaskMessage(Task task, int uncompletedSize) {
        return "Got it. I've added this task:\n" +
                "  " + task + "\n" +
                "Now you have " + uncompletedSize + " task(s) in the list.\n";
    }

    /**
     * Returns a message to the user indicating that a task has been marked as complete.
     *
     * @param task The task that has been marked.
     * @param uncompletedSize The number of uncompleted tasks in the task list.
     * @return The message indicating that a task has been marked as complete.
     */
    public static String getMarkedTaskMessage(Task task, int uncompletedSize) {
        return "Well Done! I've marked this task as done:\n" +
                "  " + task + "\n" +
                "Now you have " + uncompletedSize + " task(s) in the list.\n";
    }

    /**
     * Returns a message to the user indicating that a task has been unmarked.
     *
     * @param task The task that has been unmarked.
     * @param uncompletedSize The number of uncompleted tasks in the task list.
     * @return The message indicating that a task has been unmarked.
     */
    public static String getUnmarkedTaskMessage(Task task, int uncompletedSize) {
        return "OK, I've marked this task as not done yet:\n" +
                "  " + task + "\n" +
                "Now you have " + uncompletedSize + " task(s) in the list.\n";
    }

    /**
     * Returns a message to the user indicating that a task has been deleted from the task list.
     *
     * @param task The task that has been deleted.
     * @param uncompletedSize The number of uncompleted tasks in the task list.
     * @return The message indicating that a task has been deleted from the task list.
     */
    public static String getDeletedTaskMessage(Task task, int uncompletedSize) {
        return "Noted. I've removed this task:\n" +
                "  " + task + "\n" +
                "Now you have " + uncompletedSize + " task(s) in the list.\n";
    }

    /**
     * Returns an error message to the user.
     *
     * @param e The exception that was thrown.
     * @return The error message.
     */
    public static String showException(SundayException e) {
        return e.getMessage();
    }

    /**
     * Returns a goodbye message to the user.
     *
     * @param didSave Indicates whether the task list was saved before the program exited.
     * @return The goodbye message.
     */
    public static String getGoodbyeMessage(boolean didSave) {
        StringBuilder sb = new StringBuilder();
        if (didSave) {
            sb.append("Okay, I've save your list for the next session!\n");
        }
        sb.append("Goodbye and have a pleasant day!\n");
        return sb.toString();
    }

    /**
     * Returns the task list in a specific format to the user.
     *
     * @param taskList The task list to be printed.
     * @return The task list in a specific format to the user.
     */
    public static String getListFoundMessage(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        if (taskList.isEmpty()) {
            sb.append("Looks like you don't have any tasks matching that description.\n");
        } else {
            sb.append("Here are the task(s) I've found:\n");
            String[] strArr = taskList.toString().split("\n");
            for (int i = 0; i < strArr.length; i++) {
                sb.append(i + 1).append(". ").append(strArr[i]).append("\n");
            }
        }
        return sb.toString();
    }
}
