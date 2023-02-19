package james.jamesbot;

import james.command.AddDeadlineCommand;
import james.command.AddEventCommand;
import james.command.AddMarkCommand;
import james.command.AddToDoCommand;
import james.command.AddUnmarkCommand;
import james.command.DeleteCommand;
import james.command.ExitCommand;
import james.command.FindCommand;
import james.command.HelpCommand;
import james.command.ListTasksCommand;
import james.task.Task;
import james.task.TaskList;

/**
 * Text UI of the application.
 */
public class Ui {

    // Command help prefix.
    private static final String HELP_PREFIX = " > ";


    /**
     * Prints farewell words to users.
     */
    public String displayLeaveChat() {
        String goodbyeWords = "Good work today!\n Hope to see you again soon";
        return goodbyeWords;
    }

    /**
     * Tells user that their task has been added to the task list.
     *
     * @param task The task to be added.
     * @param tasksLength The length of the task list.
     */
    public String displayAddTask(Task task, int tasksLength) {
        String addToDoString = "You have added new tasks"
                + "\n Added: " + task.toString()
                + "\n You have " + tasksLength + " task(s)";
        return addToDoString;
    }


    /**
     * Displays the tasks in the task list.
     *
     * @param tasks The stored TaskList.
     */
    public String displayTasks(TaskList tasks) {
        boolean hasNoTasks = tasks.size() == 0;

        if (hasNoTasks) {
            return "There are no tasks in your list!";
        } else {
            return "Here are your tasks!" + tasks.taskListToString();
        }
    }


    /**
     * Tells user that their task has been marked.
     *
     * @param task The marked task.
     */
    public String displayMarkedTask(Task task) {
        return "Task has been marked!\n" + task.toString();
    }

    /**
     * Tells user that their task has been unmarked.
     *
     * @param task The unmarked task.
     */
    public String displayUnmarkedTask(Task task) {
        return "Task has been unmarked!\n" + task.toString();
    }

    /**
     * Tells user that their task has been deleted.
     *
     * @param task The deleted task.
     * @param tasksLength The length of the task list.
     */
    public String displayDeletedTask(Task task, int tasksLength) {
        return "Removing this task from your list\n"
                + task.toString()
                + "\nTask removed! Now you have " + (tasksLength) + " task(s)";
    }


    /**
     * Displays the tasks found containing the keyword.
     *
     * @param tasks The tasks containing the keyword.
     * @return The list of tasks containing the keyword.
     */
    public String displayFoundTask(TaskList tasks) {
        boolean hasFoundNoTasks = tasks.size() == 0;

        if (hasFoundNoTasks) {
            return "No tasks matching this keyword";
        } else {
            return "Here are the tasks i found that match your keyword!" + tasks.taskListToString();
        }
    }


    /**
     * Displays the error encountered during execution.
     * @param e The exception message.
     */
    public String showError(String e) {
        return e;
    }

    //@@author elizabethhky@github.com-reused
    //Reused from https://github.com/nus-cs2103-AY2223S1/ip/commit/81e454e31671a1bfe795abd6ce624d5b9b51060c
    // with minor modifications
    /**
     * Displays JamesBot program usage instructions.
     *
     * @return The instructions to use JamesBot.
     */
    public String displayCommands() {
        return HELP_PREFIX + AddToDoCommand.MESSAGE + "\n"
                + HELP_PREFIX + AddDeadlineCommand.MESSAGE + "\n"
                + HELP_PREFIX + AddEventCommand.MESSAGE + "\n"
                + HELP_PREFIX + AddMarkCommand.MESSAGE + "\n"
                + HELP_PREFIX + AddUnmarkCommand.MESSAGE + "\n"
                + HELP_PREFIX + DeleteCommand.MESSAGE + "\n"
                + HELP_PREFIX + ListTasksCommand.MESSAGE + "\n"
                + HELP_PREFIX + FindCommand.MESSAGE + "\n"
                + HELP_PREFIX + ExitCommand.MESSAGE + "\n"
                + HELP_PREFIX + HelpCommand.MESSAGE;
    }
    //@@author
}

