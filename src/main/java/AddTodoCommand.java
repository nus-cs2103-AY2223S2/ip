public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new ToDoTask(description);
        taskList.add(task);
        ui.printMessage("added: " + task);
    }
}
