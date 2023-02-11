public class ToDoCommand extends Command {

    private String name;

    public ToDoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ToDo td = new ToDo(name);
        ui.showConfirmation(tasks.addTask(td));
        storage.saveToFile(tasks.tasks);
    }
}
