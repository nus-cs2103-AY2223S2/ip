public class TodoCommand extends Command {
    private String title;

    TodoCommand(String title) {
        this.title = title;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Todo(this.title);
        tasks.add(newTask);
        ui.showAdd(newTask);
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
