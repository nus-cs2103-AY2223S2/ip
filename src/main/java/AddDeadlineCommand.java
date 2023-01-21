public class AddDeadlineCommand extends Command {
    private Deadline deadline;

    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute() throws DukeException {
        taskList.addTask(deadline);
        ui.printTaskAdded(deadline, taskList.getSize());
    }

}
