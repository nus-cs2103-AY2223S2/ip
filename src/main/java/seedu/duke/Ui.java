/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke;

import seedu.duke.task.Task;

/**
 * Represents an Ui interface.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Welcome message.
     *
     * @return The String for Duke reply.
     */
    public String welcome(String commands) {
        //Logo of Duke
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        logo = "Hello from\n" + logo;
        logo += "Here are the lists of commands:\n";
        logo += commands;
        return logo;
    }

    /**
     * Loading error message.
     *
     * @return The String for Duke reply.
     */
    public String showLoadingError() {
        String uiText = "File not found\n" + "Creating temporary Task List.\n";
        return uiText;
    }

    /**
     * Task Marked message.
     *
     * @param tasks The TaskList.
     * @param userParse The input from the user.
     * @return The String for Duke reply.
     */
    public String markDisplay(TaskList tasks, Parser userParse) {
        String uiText = "Nice! I've marked this task as done:\n";
        uiText += tasks.tasksList.get(Integer.parseInt(userParse.inputArr[1]) - 1).toString() + "\n";
        return uiText;
    }

    /**
     * Task Unmarked message.
     *
     * @param tasks The TaskList.
     * @param userParse The input from the user.
     * @return The String for Duke reply.
     */
    public String unmarkDisplay(TaskList tasks, Parser userParse) {
        String uiText = "OK, I've marked this task as not done yet:\n";
        uiText += tasks.tasksList.get(Integer.parseInt(userParse.inputArr[1]) - 1).toString() + "\n";
        return uiText;
    }

    /**
     * List message.
     *
     * @param tasks The TaskList.
     * @return The String for Duke reply.
     */
    public String list(TaskList tasks) {
        int numbering = 1;
        String uiText = "";
        for (int i = 0; i < tasks.tasksCounter; i++) {
            uiText += numbering + ". " + tasks.tasksList.get(i).toString() + "\n";
            numbering++;
        }
        return uiText;
    }

    /**
     * Added Todo message.
     *
     * @param tasks The TaskList.
     * @return The String for Duke reply.
     */
    public String addTodoMessage(TaskList tasks) {
        String uiText = "Got it. I've added this task:\n";
        uiText += tasks.tasksList.get(tasks.tasksCounter - 1).toString() + "\n";
        uiText += "Now you have " + tasks.tasksCounter + " task(s) in the list.\n";
        return uiText;
    }

    /**
     * Added Deadline message.
     *
     * @param tasks The TaskList.
     * @return The String for Duke reply.
     */
    public String addDeadlineMessage(TaskList tasks) {
        String uiText = "Got it. I've added this task:\n";
        uiText += tasks.tasksList.get(tasks.tasksCounter - 1).toString() + "\n";
        uiText += "Now you have " + tasks.tasksCounter + " task(s) in the list.\n";
        return uiText;
    }

    /**
     * Added Event message.
     *
     * @param tasks The TaskList.
     * @return The String for Duke reply.
     */
    public String addEventMessage(TaskList tasks) {
        String uiText = "Got it. I've added this task:\n";
        uiText += tasks.tasksList.get(tasks.tasksCounter - 1).toString() + "\n";
        uiText += "Now you have " + tasks.tasksCounter + " task(s) in the list.\n";
        return uiText;
    }

    /**
     * Delete message.
     *
     * @param tasks The TaskList.
     * @param deleted The Task deleted.
     * @return The String for Duke reply.
     */
    public String deleteMessage(TaskList tasks, Task deleted) {
        String uiText = "Noted. I've removed this task:\n";
        uiText += deleted.toString() + "\n";
        uiText += "Now you have " + tasks.tasksCounter + " task(s) in the list.\n";
        return uiText;
    }

    /**
     * Goodbye message.
     *
     * @return The String for Duke reply.
     */
    public String bye() {
        String uiText = "Bye. Hope to see you again soon!\n";
        return uiText;
    }

    /**
     * Found or not found matching tasks message.
     *
     * @param tasks The temporary TaskList for the find.
     * @return The String for Duke reply.
     */
    public String findMessage(TaskList tasks) {
        String uiText = "";
        if (tasks.tasksList.isEmpty()) {
            uiText += "Sorry! There are no matching tasks in your list.\n";
        } else {
            uiText += "Here are the matching tasks in your list:\n";
            uiText += list(tasks);
        }
        return uiText;
    }

    /**
     * Throws a DukeException when there is no list or find command used yet.
     *
     * @param tasks The list of tasks.
     * @throws DukeException if there is no list or find command used.
     */
    public void noListError(TaskList tasks) throws DukeException {
        String message = "You have not used the list or find command yet!\n Here is the list of tasks:\n";
        message += list(tasks);
        message += "Enter your command again. :)\n";
        throw new DukeException(message);
    }

    /**
     * Returns the tag message for the display.
     *
     * @param tasks The list of tasks.
     * @param userParse The user input.
     * @return The tag message for the display.
     */
    public String tagDisplay(TaskList tasks, Parser userParse) {
        String uiText = "Nice! I've tagged this task:\n";
        uiText += tasks.tasksList.get(Integer.parseInt(userParse.inputArr[1]) - 1).toString() + "\n";
        return uiText;
    }
}
