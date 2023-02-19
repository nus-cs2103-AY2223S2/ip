public class AddTodoCommand extends Command {
    private String description;

    AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        Ui.ShowAddMessage(todo, tasks.size());
        storage.save(tasks.getAllTasks());
    }
}