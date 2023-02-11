public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.getFarewellMessage();
        System.exit(0);
    }
}
