package duke.commands;
import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class ListCommand extends Command{
    public ListCommand(){
        super("LIST");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.list();
    }
}
