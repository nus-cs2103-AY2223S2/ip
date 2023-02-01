package command;

import io.Storage;
import task.TaskList;
import util.Parser;

public interface Command {
    public void execute(TaskList duke, Storage<TaskList> storage);

    default boolean isExit() {
        return false;
    }

    public static Parser<Command> parser() {
        return Add.parser()
                .or(Mark.parser())
                .or(Unmark.parser())
                .or(List.parser())
                .or(FindByDate.parser())
                .or(Delete.parser())
                .or(Exit.parser());
    }
}
