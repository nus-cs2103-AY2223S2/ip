package duke.command;

import duke.storage.Note;
import duke.storage.TaskList;

public class HelpCommand extends Command {
    String helpMessage = "Here is the list of features:\n\n" +
            "todo <task name>\n" +
            "deadline <deadline name> /by <deadline time>*\n" +
            "event <event name> /from <start time> /by <end time>*\n" +
            "delete\n" +
            "clear\n" +
            "list\n" +
            "mark <task index>\n" +
            "unmark <task index>\n" +
            "note <note>\n" +
            "opennotes\n\n" +
            "Note that time has to be in 'DD/MM/YYYY HH:mm' format.";

    /**
     * Constructor for Command class.
     *
     * @param userInput
     */
    public HelpCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList tasks, Note notes) {
        return helpMessage;
    }
}
