public class MarkCommand extends Command{
    private TodoList todoList;
    private int index;

    public MarkCommand(TodoList todoList, int index) {
        this.todoList = todoList;
        this.index = index;
    }

    @Override
    public void execute() throws DukeExceptions { todoList.mark(index); }
}
