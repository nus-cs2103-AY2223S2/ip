package duke.command;


public class FindCommand extends Command {

    private String word;

    public FindCommand(String word) {
        this.word = word;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(duke.TaskList tasks, duke.Ui ui, duke.Storage storage) {
        ui.subListTaskResponse(tasks.findRelated(this.word));
    }

}
