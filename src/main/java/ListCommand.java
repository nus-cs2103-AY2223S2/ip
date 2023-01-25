public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        ui.showList(task);
    }
}
