public class DeleteCommand extends Command {
    private String deleteAtIndex;

    DeleteCommand(String index) {
        this.isExit = false;
        this.deleteAtIndex = index;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws Exception {
        try {
            int i = Integer.valueOf(deleteAtIndex) - 1;
            Task deletedTask = tl.deleteTask(i);
            ui.reply("Removed " + deletedTask.getDesc() + " from the list. You now have " + tl.size() + " tasks left.");
        } catch (NumberFormatException e) {
            throw e;
        }
    }
}
