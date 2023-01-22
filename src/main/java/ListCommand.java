public class ListCommand extends Command {

    private final String NAME = "list";

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.displayTasks();
    }
}
