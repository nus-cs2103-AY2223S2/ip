public class ToDoCommand extends Command {
    private static final String TODO_COMMAND = "todo";
    private final Todo todo;

    public ToDoCommand(Todo todo) {
        super(TODO_COMMAND);
        this.todo = todo;
    }

    @Override
    public void execute(TaskList lst, Ui ui) throws DukeException {
        lst.addTask(this.todo);
        ui.showTodo(this.todo, lst.getSize());
    }
}
