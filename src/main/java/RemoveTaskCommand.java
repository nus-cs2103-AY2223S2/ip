public class RemoveTaskCommand extends Command {
    private int index;

    public RemoveTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute() throws DukeException {
        if (index >= taskList.getSize()) {
            throw new DukeException("Task number is out of range!");
        }
        Task removedTask = taskList.deleteTask(index);
        ui.printTaskDeleted(removedTask, taskList.getSize());
    }

}
