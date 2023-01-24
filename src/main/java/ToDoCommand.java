public class ToDoCommand extends AddTaskCommand {
    @Override
    public String getCommandName() {
        return "todo";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^todo (.*)$";
    }

    @Override
    public String getCommandPattern() {
        return "todo <description>";
    }

    @Override
    public void run(String[] args, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        String description = args[0];

        addTask(new ToDo(description), ui, taskList, storage);
    }
}
