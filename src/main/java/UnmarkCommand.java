public class UnmarkCommand extends Command{
    private TodoList todoList;
    private int index;

    public UnmarkCommand(TodoList todoList, int index) {
        this.todoList = todoList;
        this.index = index;
    }

    @Override
    public void execute() throws DukeExceptions{
        todoList.unmark(index);
    }
}
