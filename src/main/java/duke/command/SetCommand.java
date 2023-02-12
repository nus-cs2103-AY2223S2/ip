package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.enums.Commands;
import duke.enums.Languages;
import duke.enums.Views;

/**
 * Command: Sets the language of Duke
 */
public class SetCommand extends Command {
    private String input;

    /**
     * Creates Set Command
     *
     * @param input string from user
     */
    public SetCommand(String input) {
        this.input = input.replace(Commands.SET.cmd(), "");
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @throws DukeException
     */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        Languages lang = execute();
        Ui.showSetLang(lang);
    }

    private Languages execute() throws DukeException {
        try {
            Languages selectedLanguage = Languages.valueOf(this.input.toUpperCase().trim());
            Duke.setLang(selectedLanguage);
            return selectedLanguage;
        } catch (IllegalArgumentException e) {
            throw new DukeException(Views.INVALID_LANG_ERR_STRING.str() + Languages.listEnumStrings());
        }
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @return returns the UI text instead of printing
     * @throws DukeException
     */
    public String executeString(TaskList tasks, Storage storage) throws DukeException {
        Languages lang = execute();
        return Ui.stringSetLang(lang);
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
