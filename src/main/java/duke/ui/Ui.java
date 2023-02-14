package duke.ui;

import java.util.List;

import duke.Duke;
import duke.task.Task;

/**
 * The Ui class implements the user interactions of the user.
 *
 * @author Chia Jeremy
 */
public class Ui {

    private String message;
    private List<Task> tasks;

    /**
     * Returns the response.
     *
     * @return the response
     */
    public String getResponse() {
        return this.message;
    }

    /**
     * Sets the response to display.
     *
     * @param message the response to display
     */
    public void setResponse(String message) {
        this.message = message;
    }

    /**
     * Returns the tasks.
     *
     * @return the tasks
     */
    public List<Task> getTasksToDisplay() {
        return this.tasks;
    }

    /**
     * Sets the tasks to display.
     *
     * @param tasks the tasks to display
     */
    public void setTasksToDisplay(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Duke greets the user when it boots up.
     *
     * @return the greeting message
     */
    public String greet() {
        return "Hello I'm\n" + Duke.LOGO + "What can I do for you?\n\n" + showCommands();
    }

    /**
     * Returns all available commands.
     *
     * @return all commands
     */
    public String showCommands() {
        return "MY COMMANDS ARE:\n"
                + "01. ADD TODO TASK:       todo [descr]\n"
                + "02. ADD DEADLINE TASK:   deadline [descr] /by [YYYY-MM-DD HH:MM]\n"
                + "03. ADD EVENT TASK:      event [descr] /from [YYYY-MM-DD HH:MM] /to [YYYY-MM-DD HH:MM]\n"
                + "04. MARK A TASK:         mark [index]\n"
                + "05. UNMARK A TASK:       unmark [index]\n"
                + "06. DELETE A TASK:       delete [index]\n"
                + "07. DISPLAY COMMANDS:    help\n"
                + "08. LIST ALL TASKS:      list\n"
                + "09. DISPLAY COMMANDS:    help\n"
                + "10. EXIT PROGRAM:        bye";
    }
}
