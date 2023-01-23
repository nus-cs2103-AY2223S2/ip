public class AddCommand extends Command {
    private String args;
    AddCommand(String args) {
        this.isExit = false;
        this.args = args;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.reply("this is the add command for the following arguments: " + this.args);
    }
}
