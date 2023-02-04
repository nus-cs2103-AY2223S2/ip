public class ListCommand extends Command{
    private TodoList todoList;

    public ListCommand(TodoList todoList) {
        this.todoList = todoList;
    }

    @Override
    public void execute() throws DukeExceptions{
        String shown_list = todoList.toString();
        System.out.println(shown_list);
    }
}

