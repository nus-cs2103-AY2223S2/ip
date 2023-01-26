public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        System.out.println(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
