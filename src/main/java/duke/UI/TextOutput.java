package duke.UI;
import duke.task.*;

/**
 * Displays the command line message.
 */
public class TextOutput {

    public static String makeDeleteString(Task deletedTask, int taskCount) {
        return "Noted. I've removed this task:\n" + deletedTask +
                "\n" + "Now you have " +  taskCount + " tasks in the list.";
    }

    public static String makeAddString(String task, int number) {
        return "Got it. I've added this task: \n" + task
                + "\n" + "Now you have " + number + " tasks in the list.";
    }

    public static String makeEchoString(String str){
        return str;
    }

    public static String makeByeString() {
        return "Hope to see you again soon!";
    }

    public static String makeTaskFoundString(TaskList taskList) {
        return "Here are the matching tasks in your list: \n"
                + taskList.listTasks();
    }

    public static String makeTaskNotFoundString() {
        return "Sorry, there is no such tasks.";
    }

    public static String makeListTaskString(TaskList taskList) {
        return "Here are the tasks in your list: \n"
                + taskList.listTasks();
    }

    public static String makeMarkDoneString(TaskList taskList, int taskNumber) {
        return "Nice! I've marked this task as done:\n" + "[" + taskList.getTaskIcon(taskNumber)
                + "] " + taskList.getTaskContent(taskNumber);
    }

    public static String makeMarkUndoneString(TaskList taskList, int taskNumber) {
        return "OK, I've marked this task as not done yet:\n" + "[" + taskList.getTaskIcon(taskNumber)
                + "] " + taskList.getTaskContent(taskNumber);
    }

    public static String makeSuccessLoadString() {
        return "Successfully retrieved past task list.";
    }

    public static String makeUnsuccessLoadString() {
        return "No past task list found, created new task list.";
    }

    public static String makeSucessSaveString() {
        return "Successfully saved your current tasks.";
    }

    public static String makePostponeString(Task task) {
        return "Successfully updated your task time to the following: \n"
                + task.toString();
    }

    public static String makeDisplayFailString() {
        return "Sorry, the indicated task type cannot be postponed. \n";
    }

    public static String makeUnsuccessSaveString(Exception e) {
        return "Sorry, unable to save your current tasks due to " + e.getMessage();
    }

    public static String makeInvalidUpdateString() {
        return "Sorry, your update command is not supported \n"
                + "Please be reminded that the date format: \n"
                + "update [task_index] [d/MM/yyyy HHmm] [d/MM/yyyy HHmm] for events \n"
                + "or update [task_index] [d/MM/yyyy HHmm] for todos";
    }

    public static String makeErrorString() {
        return "There seems to be an unknown error with your command, please rephrase and try again. \n";
    }
}
