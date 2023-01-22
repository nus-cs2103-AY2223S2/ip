public class UnmarkCommand extends Command {

    private final String NAME = "unmark";
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.markTaskAsUndone(this.index);
    }
}
