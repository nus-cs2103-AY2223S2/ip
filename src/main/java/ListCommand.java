public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // do the commands purpose
        // ui.print() the correct output
        String str = "List:";
        for (int i = 1; i <= tasks.size(); i++) {
            str += String.format("\n\t%d. %s", i, tasks.get(i));
        }
        ui.print(str);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
