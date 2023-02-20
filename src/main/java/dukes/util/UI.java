package dukes.util;

import dukes.task.Task;

import java.util.Scanner;

/**
 * The util class for reading inputs and providing feedbacks to user.
 */
public class UI {
    // mainly use static methods
    // need to handle the exceptions raised by Parser
    public static final String WORD_DIVISHION_LINE = "________________________________________";
    public static final String WORD_LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String WORD_GREET = "It's a pleasure to serve you!";
    public static final String WORD_BYE = "Goodbye. Hope you have a nice day!";
    public static final String WORD_MARK_DONE =
            "Nice! You have completed this task: ";
    public static final String WORD_MARK_UNDONE =
            "Well, you have not finished this task yet: ";
    public static final String WORD_FIND = "Here are the matching tasks in list:  ";
    public static final String WORD_LIST = "Here are all of your tasks: ";
    public static final String WORD_SEARCH = "Here are your tasks on the given date: ";
    public static final String WORD_ADD = "This task is added to your list: ";
    public static final String WORD_DELETE = "Ok, I will remove this task for you: ";
    public static final String NOW_YOU_HAVE = "Now you have ";
    public static final String TASK_IN_LIST = " tasks in the list.";

    public static final String WORD_UPDATE = "You have successfully updated this task: ";


    /**
     * Display error message when data file is not found in the hard disk.
     *
     * @param msg error message to be displayed.
     */
    public static void showLoadingError(String msg) {
        System.out.println(msg);
    }

    /**
     * Private util method to extract duplicate code
     *
     * @param theTask the target task
     * @param header word to be said by Duke
     * @return the StringBuilder containing the header and task
     */
    private static StringBuilder getStringBuilder(Task theTask, String header) {
        StringBuilder sb = new StringBuilder();
        sb.append(header).append("\n");
        sb.append(theTask.toString()).append("\n");
        return sb;
    }

    /**
     * Display success message when user add tasks into list.
     *
     * @param theTask the task added into the list.
     * @param tasks the list of tasks.
     * @return the success message.
     */
    public static String returnAdd(Task theTask, TaskList tasks) {
        return getUpdateResponse(theTask, tasks, WORD_ADD);
    }

    /**
     * Display success message when user delete tasks from list.
     *
     * @param theTask the task deleted from the list.
     * @param tasks the list of tasks.
     * @return the delete message.
     */
    public static String returnDelete(Task theTask, TaskList tasks) {
        return getUpdateResponse(theTask, tasks, WORD_DELETE);
    }

    /**
     * Display success message when user list all the tasks,
     * or list the tasks on the specific date.
     *
     * @param str a string containing the list of tasks to be displayed.
     * @param action 0 for list, 1 for search.
     * @return the list string.
     */
    public static String returnList(String str, int action) {
        return getConditionalResponse(str, action, WORD_LIST, WORD_SEARCH);
    }

    /**
     * Display success message when user mark a task as done or undone.
     *
     * @param theTask the task to be marked.
     * @param action 0 for mark, 1 for unmark.
     * @return the mark or unmark string.
     */
    public static String returnMark(Task theTask, int action) {
        return getConditionalResponse(" " + theTask.toString(), action, WORD_MARK_DONE, WORD_MARK_UNDONE);
    }

    /**
     * Display success message when user find tasks based on specific pattern.
     *
     * @param str the find keyword.
     * @return the find output.
     */
    public static String returnFind(String str) {
        return WORD_FIND + "\n" + str;
    }

    public static String returnUpdate(Task theTask, TaskList tasks) {
        return getUpdateResponse(theTask, tasks, WORD_UPDATE);
    }

    /**
     * To provide the updated response for add and delete tasks
     * @param theTask
     * @param tasks
     * @param header
     * @return
     */
    private static String getUpdateResponse(Task theTask, TaskList tasks, String header) {
        StringBuilder sb = new StringBuilder();
        sb.append(header).append("\n");
        sb.append(theTask.toString()).append("\n");
        int numTask = tasks.getTaskList().size();
        sb.append(NOW_YOU_HAVE + numTask +
                TASK_IN_LIST);
        return sb.toString();
    }

    /**
     * To provide the response for list/search, mark/unmark command
     *
     * @param str target string
     * @param action 0 or 1
     * @param header0 used if action = 0
     * @param header1 used if action = 1
     * @return the output string
     */
    private static String getConditionalResponse(String str, int action, String header0, String header1) {
        StringBuilder sb = new StringBuilder();
        if (action == 0) {
            sb.append(header0);
        } else {
            sb.append(header1);
        }
        sb.append("\n");
        sb.append(str);
        return sb.toString();
    }

}
