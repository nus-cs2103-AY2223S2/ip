public class ToDoCommand extends Command {

    private final String NAME = "todo";
    private String title;

    public ToDoCommand(String title) {
        this.title = title;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Task task = new ToDo(this.title);
        taskList.addTask(task);
    }
}
