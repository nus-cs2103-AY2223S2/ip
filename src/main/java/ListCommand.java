public class ListCommand extends Command {
    @Override
    boolean isExit() {
        return false;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listItems();
    }
}
