public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskStore, Ui ui, Storage storage) {
        ui.showList(taskStore);
    }

}
