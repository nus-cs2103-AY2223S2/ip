package fea.ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import fea.task.Task;
import fea.tasklist.TaskList;

/**
 * Ui class that handles all user interactions.
 */
public class Ui {

    public static final String WELCOME = "Shielder Mash Kyrielight. \n"
            + "I'm still just an immature Servant, but I'll do what I can to help you.";
    public static final String BYE = "Bye. Hope to see you again soon!";
    public static final String NO_TASKS = "No tasks added yet.";
    private static final String NO_TASKS_LOADED = "Data file was loaded but no tasks could be found.";
    private static final String TASKS_LOADED_SUCCESSFUL = "Tasks loaded successfully!";
    private static final String TASKS_LOAD_ERROR = "Data file could not be found.\n"
            + "A new data file will be auto generated upon insert of a task.";
    private static final String ADD_TASK = "Got it. I've added this task:\n";
    private static final String REMOVE_TASK = "Noted. I've removed this task:\n";
    private static final String TASK_MARKED = "Nice! I've marked this task as done:\n";
    private static final String TASK_UNMARKED = "Noted. I've marked this task as not done:\n";
    private static final String TASKS_LIST = "Here are the tasks in your list:\n";
    private static final String NO_TASKS_MATCH = "No matching tasks found.";
    private static final String TASKS_MATCH = "Here are the matching tasks in your list:";
    private static final String SET_REMINDER = "Nice! I've set a reminder for this task:\n";
    private Logger logger = Logger.getLogger(Ui.class.getName());

    /**
     * Takes in a file path and logs an attempt to load message to the user.
     *
     * @param filePath The file path of the data file.
     */
    public void logLoading(String filePath) {
        logger.log(Level.INFO, "Trying to load tasks from {0}...", filePath);
    }

    /**
     * Logs a successful load message to the user if tasks is not empty.
     *
     * @param tasks The current task list.
     */
    public void logSuccessfulLoad(TaskList tasks) {
        if (tasks.size() == 0) {
            logger.warning(NO_TASKS_LOADED);
        } else {
            logger.info(TASKS_LOADED_SUCCESSFUL);
        }
    }

    /**
     * Logs the loading error message when the data file could not be found.
     */
    public void logLoadingError() {
        logger.warning(TASKS_LOAD_ERROR);
    }

    /**
     * Prints task when it is added or removed.
     *
     * @param task     The task to be printed.
     * @param taskList The current task list.
     * @param toAdd    True if task is to be added, false otherwise.
     * @return String The string of the printed task.
     */
    public String printTask(Task task, ArrayList<Task> taskList, boolean toAdd) {
        StringBuilder message = new StringBuilder();
        message.append(toAdd ? ADD_TASK : REMOVE_TASK);
        message.append(String.format("%s%n", task.toString()))
                .append(String.format("Now you have %d %s in the list.", taskList.size(),
                        taskList.size() == 1 ? "task" : "tasks"));
        return message.toString();
    }

    /**
     * Prints task when it is marked as done or not done.
     *
     * @param task   The task to be printed.
     * @param toMark True if task is to be marked as done, false otherwise.
     * @return String The string of the task to be marked.
     */
    public String printMarkTask(Task task, boolean toMark) {
        StringBuilder message = new StringBuilder();
        message.append(toMark ? TASK_MARKED : TASK_UNMARKED);
        message.append(String.format("%s", task.toString()));
        return message.toString();
    }

    /**
     * Prints the tasks in the task list.
     *
     * @param tasks The current task list.
     * @return String The string of the printed tasks.
     */
    public String printTasks(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        message.append(TASKS_LIST);
        for (int i = 0; i < tasks.size(); i++) {
            message.append(String.format("%d.%s", i + 1, tasks.getTask(i).toString()));
            if (i != tasks.size() - 1) {
                message.append("\n");
            }
        }
        return message.toString();
    }

    /**
     * Prints all tasks that contain the keyword.
     *
     * @param matchingTasksIndex An arraylist of the indexes of the tasks that
     *                           contain the keyword.
     * @param tasks              The current task list.
     * @return String The string of the printed tasks which contains the keyword.
     */
    public String printMatchingTasks(ArrayList<Integer> matchingTasksIndex, ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder();
        if (matchingTasksIndex.isEmpty()) {
            message.append(NO_TASKS_MATCH);
        } else {
            message.append(TASKS_MATCH);
            for (int i = 0; i < matchingTasksIndex.size(); i++) {
                int taskIndex = matchingTasksIndex.get(i);
                message.append(String.format("%d.%s", taskIndex + 1, tasks.get(taskIndex).toString()));
                if (i != matchingTasksIndex.size() - 1) {
                    message.append("\n");
                }
            }
        }
        return message.toString();
    }

    /**
     * Prints the reminder when it is set.
     * @param task The task whose reminder is set.
     * @return String The string of the printed reminder.
     */
    public String printReminderTask(Task task) {
        StringBuilder message = new StringBuilder();
        message.append(SET_REMINDER);
        message.append(String.format("%s", task.toString()));
        return message.toString();
    }

}
