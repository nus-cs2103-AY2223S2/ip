package duke.command;

import duke.storage.Note;
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
        return notes.getNotes();
    }
}
