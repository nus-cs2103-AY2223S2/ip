public class MarkCommand extends Command{
    public static final String COMMAND_WORD = "mark";
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks,Ui ui,Storage storage) {
        tasks.markTask(taskIndex);
    }
}
