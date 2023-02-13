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

    /**
     * Prints farewell words to users.
     */
    public String displayLeaveChat() {
        String goodbyeWords = "good work today!\nhope to see you again soon";
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
                + "\n added: " + task.toString()
                + "\nyou have " + String.valueOf(tasksLength) + " task(s)";
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
        return "removing this task from your list...\n\t"
                + task.toString()
                + "\ntask removed! now you have " + String.valueOf(tasksLength) + " task(s)";
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
            return "here are the tasks i found that match your keyword" + tasks.taskListToString();
        }
    }


    /**
     * Displays the error encountered during execution.
     * @param e The exception message.
     */
    public String showError(String e) {
        return e;
    }

    /**
     * Displays UwuBot program usage instructions.
     *
     * @return The instructions to use UwuBot.
     */
    public String displayCommands() {
        AddEventCommand MarkCommand;
        return "\u2022 " + AddToDoCommand.MESSAGE_USAGE
                + "\n\u2022 " + AddEventCommand.MESSAGE_USAGE
                + "\n\u2022 " + AddDeadlineCommand.MESSAGE_USAGE
                + "\n\u2022 " + DeleteCommand.MESSAGE_USAGE
                + "\n\u2022 " + AddMarkCommand.MESSAGE_USAGE
                + "\n\u2022 " + AddUnmarkCommand.MESSAGE_USAGE
                + "\n\u2022 " + FindCommand.MESSAGE_USAGE
                + "\n\u2022 " + ListTasksCommand.MESSAGE_USAGE
                + "\n\u2022 " + HelpCommand.MESSAGE_USAGE
                + "\n\u2022 " + ExitCommand.MESSAGE_USAGE;
    }

}
