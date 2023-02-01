public class TerminateCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public TerminateCommand() {
    }
    public void execute(TaskList tasks,Ui ui,Storage storage) {
        storage.saveTasks(tasks.getArrayList());
        ui.showGoodBye();
    }

    public boolean isExit() {
        return true;
    }
}
