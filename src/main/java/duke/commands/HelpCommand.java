package duke.commands;

import java.util.ArrayList;

import duke.components.Storage;
import duke.components.TaskList;
import duke.exceptions.DukeException;

/**
 * This is the HelpCommand class to represent the help command parsed by Parser.
 * Encapsulates the information needed to print instructions to the user.
 */
public class HelpCommand extends Command {
    private String helpMessage = "My objective is to help you keep your tasks and deadlines in order. The commands I'm currently familiar with are:\n" +
            "todo [todo name]\n" +
            "deadline [deadline name] /by [YYYY-MM-DD]\n" +
            "event [event name] /from [YYYY-MM-DD] /to [YYYY-MM-DD]\n" +
            "delete [index of task to delete]\n" +
            "find [keyword to search for]\n" +
            "list\n" +
            "mark [index of task to mark as done]\n" +
            "unmark [index of task to mark as not done]\n" +
            "remind [number of days to preempt tasks]\n" +
            "\n" +
            "type a command without any parameters to learn more about the command!";
    /**
     * Abstract constructor of a Command object.
     *
     * @param tokens tokenized user input.
     */
    public HelpCommand(ArrayList<String> tokens) {
        super(tokens);
    }

    /**
     * @param tasks   TaskList object to handle task management.
     * @param storage Storage object to handle reading/writing to/from storage.
     * @return
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return helpMessage;
    }

    /**
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
