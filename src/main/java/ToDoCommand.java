public class ToDoCommand extends Command {
    String description;
    public ToDoCommand(String description) {
        super(false);
        this.description = description;

    }

    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        task.addToDo(description);
        int size = task.getSize();
        Task temp = task.getTask(size - 1);
        ui.showAdd(temp, size);
    }
}
