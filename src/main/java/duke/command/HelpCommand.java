package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.enums.Commands;

/**
 * Command: Returns the help message
 */
public class HelpCommand extends Command {
    private String query;

    /**
     * Creates Help Command
     *
     * @param query string from user
     */
    public HelpCommand(String... query) {
        if (query.length == 0) {
            this.query = null;
        } else {
            // Trims the input query, spilt it and take the first word
            this.query = query[0].trim().split(" ")[0];
        }
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param ui      object to reply to user after the command has executed
     * @param storage object required when command writes to file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.showHelp(getCommandFromQuery());
        } catch (java.lang.NullPointerException e) {
            ui.showHelp();
        } catch (IllegalArgumentException e) {
            ui.showHelpError();
        }
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param ui      object to reply to user after the command has executed
     * @param storage object required when command writes to file
     * @return returns the UI text instead of printing
     */
    public String executeString(TaskList tasks, Ui ui, Storage storage) {
        try {
            return ui.stringHelp(getCommandFromQuery());
        } catch (java.lang.NullPointerException e) {
            return ui.stringHelp(true);
        } catch (IllegalArgumentException e) {
            return ui.stringHelpError(true);
        }
    }

    private Commands getCommandFromQuery() {
        Commands selectedLanguage = Commands.valueOf(this.query.toUpperCase().trim());
        return selectedLanguage;
    }

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public boolean isExit() {
        return false;
    }
}
