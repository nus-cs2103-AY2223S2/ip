public class AddCommand extends Command {
    private final char taskType;
    private final String content;

    public AddCommand(char taskType, String commandContent) {
        this.taskType = taskType;
        this.content = commandContent;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = Task.create(this.taskType, this.content);
        taskList.addTask(task);
    }
}
