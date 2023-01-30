package duke.ui;

import duke.Duke;

/**
 * The Ui class implements the user interactions of the user.
 *
 * @author Chia Jeremy
 */
public class Ui {

    private String message;

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
                + "ADD TODO TASK:     todo [description]\n"
                + "ADD DEADLINE TASK: deadline [description] /by [YYYY-MM-DD HH:MM]\n"
                + "ADD EVENT TASK:    event [description] /from [YYYY-MM-DD HH:MM] /to [YYYY-MM-DD HH:MM]\n"
                + "MARK A TASK:       mark [index]\n"
                + "UNMARK A TASK:     unmark [index]\n"
                + "DELETE A TASK:     delete [index]\n"
                + "LIST ALL TASKS:    list\n"
                + "DISPLAY COMMANDS:  help\n"
                + "EXIT PROGRAM:      bye";
    }
}
