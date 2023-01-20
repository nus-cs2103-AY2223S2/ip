public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String commandContent) {
        this.index = Parser.parseInt(commandContent, "Delete Item");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(this.index);
    }
}
