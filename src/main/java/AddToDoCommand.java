public class AddToDoCommand extends Command {
    private ToDo toDo;

    public AddToDoCommand(ToDo task) {
        this.toDo = task;
    }

    @Override
    public void execute() throws DukeException {
        taskList.addTask(toDo);
        ui.printTaskAdded(toDo, taskList.getSize());
    }

}
