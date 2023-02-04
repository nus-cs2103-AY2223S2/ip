public class DeleteCommand extends Command{
    private TodoList todoList;
    private int index;

    public DeleteCommand(TodoList todoList, int index) {
        this.todoList = todoList;
        this.index = index;
    }

    @Override
    public void execute() throws DukeExceptions{
        todoList.delete(index);
        System.out.println(String.format("Now I have %d tasks in the list.", todoList.number_of_tasks()));
    }
}
