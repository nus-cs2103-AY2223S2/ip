public class AddDeadLineCommand extends AddTaskCommand {
    public AddDeadLineCommand(String[] inputArr) {
        super(inputArr);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        DeadLine deadLine = DeadLine.generate(inputArr);
        taskList.addTask(deadLine);
        storage.writeData(taskList);
        ui.printAddTaskMsg(taskList, deadLine);
    }
}
