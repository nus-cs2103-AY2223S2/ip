public class TaskTodo extends DukeTask{
    public TaskTodo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
