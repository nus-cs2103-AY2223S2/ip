public class ToDo extends Tasks {

    private String todo;

    public ToDo(String Task_content) {
        super(Task_content);
    }

    public String get_Todo() {
        return this.todo;
    }

    public String toString() {
        return "[T] " + super.toString();
    }
}
