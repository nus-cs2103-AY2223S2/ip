public class UnmarkCommand extends Command{
    public static final String COMMAND_WORD = "unmark";
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks,Ui ui,Storage storage) {
        tasks.unmarkTask(taskIndex);
    }
}
