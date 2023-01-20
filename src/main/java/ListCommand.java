public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listItems();
    }
}
