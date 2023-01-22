public class ByeCommand extends Command {

    private final String NAME = "bye";

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        ui.addToMessage("Bye. Hope to see you again soon!");

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
