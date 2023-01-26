package duke;

public class TodoCommand extends Command {
    public TodoCommand(String input) {
        super(input);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException(
                    "You cant be doing nothing!! Please try again!");
        }
        Todo taskTodo = new Todo(input);
        taskList.addTask(taskTodo);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        ui.showTaskAdded(taskTodo);
        ui.showNumTasks(taskList);
    }
}
