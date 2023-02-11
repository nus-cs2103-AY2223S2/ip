package ui;

import java.util.ArrayList;

import data.TaskManager;
import task.Task;


/**
 * Formats the default responses for UwU_TaskMaster
 * @author Nicholas Lee
 */
public class Format {

    public static String getCompletionDisplay(boolean isCompleted) {
        String completionDisplay;
        if (isCompleted) {
            completionDisplay = "[X]";
        } else {
            completionDisplay = "[ ]";
        }
        return completionDisplay;
    }

    public static String formatResponse(String output) {
        return "\nUwU_TaskMaster ＵｗＵ: " + output;
    }

    /**
     * Prints out the users tasks
     *
     * Calls the toString() method of each task type and formats the lists of tasks
     * by printing to the console line by line
     *
     * @param showMessage description of the parameter
     * @param taskManager instance of a taskManager
     */
    public static String displayTasks(boolean showMessage, TaskManager taskManager) {
        StringBuilder response = new StringBuilder();
        ArrayList<Task> taskList = taskManager.getTasks();

        if (taskList.size() == 0) {
            return Response.NO_TASK.toString();
        }

        if (showMessage) {
            response.append("Heww are your tasks UwU!\n");
        }

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String lineItem = (i + 1) + ". " + task.toString() + "\n";
            response.append(lineItem);
        }
        return response.toString();
    }


    /**
     * Prints out the tasks matching a user's keywords as part of "find" feature
     *
     * Calls the toString() method of each task type and formats the lists of tasks
     * by printing to the console line by line
     *
     * @param taskManager instance of a taskManager
     */
    public static String displayFilteredTasks(TaskManager taskManager) {
        StringBuilder response = new StringBuilder();
        ArrayList<Task> taskList = taskManager.getTasks();

        if (taskList.size() == 0) {
            return Response.NO_MATCHING_TASKS.toString();
        }

        String message = "I found " + taskList.size() + " matching tasks in your list UwU!\n";

        response.append(message);

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String lineItem = (i + 1) + ". " + task.toString() + "\n";
            response.append(lineItem);
        }
        return response.toString();
    }
}
