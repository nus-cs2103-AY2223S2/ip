public class ByeCommand extends Command {

    private final String NAME = "bye";

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.addToMessage("Bye. Hope to see you again soon!");
    }
}
