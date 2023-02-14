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
        String logo = "$$$$$___ _______ $$_____ _______\n"
                + "$$__$$__ _______ $$__$$_ _$$$$__\n"
                + "$$___$$_ $$__$$_ $$_$$__ $$__$$_\n"
                + "$$___$$_ $$__$$_ $$$$___ $$$$$$_\n"
                + "$$__$$__ $$__$$_ $$_$$__ $$_____\n"
                + "$$$$$___ _$$$_$_ $$__$$_ _$$$$$_\n";
        logo = "Hello from\n" + logo;
        logo += "How may i help you today?\n";
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
        String uiText = "Beep Boop! I've marked this task as done:\n";
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
        String uiText = "Roger that, I've marked this task as not done yet:\n";
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
        String uiText = "On it, my MaStEr. I've successfully added this task:\n";
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
        String uiText = "Deadline time!!! I've successfully added this task:\n";
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
        String uiText = "Oohhh look what we have here. I've added this task:\n";
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
        String uiText = "Time to eliminate this task:\n" + "*slays this task*\n";
        uiText += deleted.toString() + "\n" + "*tasks disappeared successfully*\n";
        uiText += "Now you have " + tasks.tasksCounter + " task(s) in the list.\n";
        return uiText;
    }

    /**
     * Goodbye message.
     *
     * @return The String for Duke reply.
     */
    public String bye() {
        String uiText = "Good Bye, my youngling.\n";
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
            uiText += "Sorry! No matching tasks in your list.\n";
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
        String uiText = "HashTag Time!!! I've tagged this task:\n";
        uiText += tasks.tasksList.get(Integer.parseInt(userParse.inputArr[1]) - 1).toString() + "\n";
        return uiText;
    }
}
