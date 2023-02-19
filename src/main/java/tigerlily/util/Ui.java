package tigerlily.util;

import tigerlily.exceptions.DukeExceptions;

import tigerlily.tasks.Task;
import tigerlily.tasks.TaskList;

public class Ui {
    /**
     * Displays Tigerlily To-Do's welcome message when user starts session.
     */
    public String showWelcome() {
        return "｡ﾟﾟ･｡･ﾟﾟ｡\n" + "ﾟ。   welcome to tigerlily to-do\n" + "　ﾟ･｡･ﾟ\n" +
                "let's organize your life\n" + "try 'help' for guidance";
    }

    /**
     * Displays Tigerlily To-Do's goodbye message when user ends session.
     */
    public String showBye() {
        return "｡ﾟﾟ･｡･ﾟﾟ｡\n" + "ﾟ。   bye, see you again soon!\n" + "　ﾟ･｡･ﾟ\n";
    }

    /**
     * Displays the given message.
     *
     * @param message the String to be displayed
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Displays the confirmation message when a Task has been successfully added to the TaskList.
     *
     * @param task the Task which has been added successfully
     * @param taskList the TaskList which the Task has been added to
     */
    public String showAddedMessage(Task task, TaskList taskList) {
        return "okay perf, your task: " + task.toString() + " has been added to your list\n" +
                "there are now " + taskList.getSize() + " task(s) in your list\n";
    }

    /**
     * Displays the error message when a DukeException has been encountered.
     */
    public String showError(DukeExceptions error) {
        return error.toString();
    }

    public String showHelp() {
        return "here is a list of instructions Tigerlily can handle:\n\n" +
                "❀ deadline [description] /by [deadline due] - creates a new Deadline\n" +
                "❀ event [description] /from [event start] /to [event end] - creates a new Event\n" +
                "❀ todo [description] - creates a new ToDo\n\n" +
                "❀ find [query] - finds Tasks related to the query\n" +
                "❀ list - lists out all Tasks in TaskList\n\n" +
                "❀ delete [index # of Task] - deletes corresponding Task\n" +
                "❀ mark [index # of Task] - marks corresponding Task as completed\n" +
                "❀ unmark [index # of Task] - marks corresponding Task as uncompleted\n\n" +
                "❀ bye - closes Tigerlily application\n";
    }
}