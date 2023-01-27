package seedu;

public class addToDoCommand extends Command {
    private ToDo toDo;

    public addToDoCommand(ToDo task) {
        this.toDo = task;
    }

    @Override
    public void execute() throws JamesException {
        taskList.addToTaskList(toDo);
        ui.addTask(toDo, taskList.getSize());
    }

}
