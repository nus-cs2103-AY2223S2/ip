package duke.command;

import duke.storage.Note;
import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * Class for OpenNoteCommand
 */
public class OpenNoteCommand extends Command {
    /**
     * Constructor for Command class.
     *
     * @param userInput
     */
    public OpenNoteCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes user input and displays current notes
     * @param tasks Current TaskList.
     * @param notes Current Note.
     * @return Current notes.
     */
    @Override
    public String execute(TaskList tasks, Note notes) {
        if (Storage.loadNotesFromFile().equals("")) {
            return "You currently do not have any notes.";
        } else {
            return "Here are your current notes:\n" + Storage.loadNotesFromFile();
        }
    }
}
