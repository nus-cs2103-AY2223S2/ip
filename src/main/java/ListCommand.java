public class ListCommand extends Command {

    ListCommand(TaskList taskList) {
        super(taskList, false);
    }

    @Override
    public void execute() {
        super.taskList.listTasks();
    }

}
