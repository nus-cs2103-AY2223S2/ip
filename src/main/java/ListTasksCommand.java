public class ListTasksCommand extends Command {
    public ListTasksCommand() {
    }

    public void assign(TaskList taskList, UI ui) {
        super.assign(taskList, ui);
    }

    @Override
    public void execute() throws DukeException {
        ui.printTaskList(taskList);
    }

}
