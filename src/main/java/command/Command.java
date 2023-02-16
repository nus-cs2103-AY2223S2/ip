package command;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

/**
 * Interface for how a command will interact with data and output.
 */
public interface Command {
    /**
     * @return Parser<Command> that can parse input string and turn it into a
     * command.
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
                .or(Sort.parser());
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
