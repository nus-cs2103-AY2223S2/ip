public class DeleteCommand extends AddCommand{
    public static final String COMMAND_WORD = "delete";
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks,Ui ui,Storage storage) {
        tasks.deleteTask(taskIndex);
    }
}