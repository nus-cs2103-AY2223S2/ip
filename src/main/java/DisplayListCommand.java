public class DisplayListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printMessage("Your tasks are:\n" + taskList.toString());
    }
}
