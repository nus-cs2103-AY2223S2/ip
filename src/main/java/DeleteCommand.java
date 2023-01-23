public class DeleteCommand extends Command {
    private String deleteAtIndex;

    DeleteCommand(String index) {
        this.isExit = false;
        this.deleteAtIndex = index;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.reply("delete at " + this.deleteAtIndex);
    }
}
