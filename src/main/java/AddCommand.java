public class AddCommand extends Command {

    private final Task task;
    private final String taskType;

    AddCommand(TaskList taskList, Task task, String taskType) {
        super(taskList, false);
        this.task = task;
        this.taskType = taskType;

    }

    @Override
    public void execute() {
        super.taskList.addTask(this.task, this.taskType);
    }

}
