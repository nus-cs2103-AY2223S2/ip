package duke.commands;
import duke.Ui;
import duke.dukeexceptions.UnknownCommandException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class UnknownCommand extends Command{
    public UnknownCommand(){
        super("UNKNOWN");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)throws UnknownCommandException{
            throw new UnknownCommandException();
    }
}
