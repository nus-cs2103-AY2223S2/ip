public class AddToDoCommand extends AddTaskCommand {
    public AddToDoCommand(String[] inputArr) {
        super(inputArr);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo toDo = ToDo.generate(inputArr);
        taskList.addTask(toDo);
        storage.writeData(taskList);
        ui.printAddTaskMsg(taskList, toDo);
    }
}
