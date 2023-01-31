package duke.commands;
import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class EndCommand extends Command{
    public EndCommand(){
        super("END");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String bye = "  Bye! have a great day";
        System.out.println(bye);
        return;
    }
}
