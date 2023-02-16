package command;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

/**
 * Interface for how a command will interact with data and output.
 */
public interface Command {
    String ERROR = "Sorry I did not understand. "
            + "Type [help 'command'] to get help for command or "
            + "[help all] to see list of commands available.";

    /**
     * @return Parser that can parse input string and turn it into a command.
     * @see Parser
     */
    static Parser<Command> parser() {
        return Add.parser()
                .or(Mark.parser())
                .or(Unmark.parser())
                .or(List.parser())
                .or(FindByDate.parser())
                .or(Delete.parser())
                .or(Exit.parser())
                .or(Find.parser())
                .or(Sort.parser())
                .or(Help.parser())
                .overrideMsg(ERROR);
    }

    /**
     * Interacts with inputs to produce required side effects.
     *
     * @param taskList TaskList to be modified.
     * @param storage  Tells storage to store/extract data if command requires it.
     * @see TaskList
     * @see Storage
     */
    void execute(TaskList taskList, Ui ui, Storage<TaskList> storage);

    /**
     * @return true if Duke should exit after executing this command.
     */
    default boolean isExit() {
        return false;
    }
}
