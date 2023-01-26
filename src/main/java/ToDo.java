public class ToDo extends Task {

    private String todo;

    public ToDo(String Task_content) {
        super(Task_content);
    }

    public ToDo(boolean isMarked, String content) {
        super(isMarked, content);
    }

    public String get_Todo() {
        return this.todo;
    }

    @Override
    public String addDivider() {
        String divider = " | ";
        int marked = this.isMarked() ? 1 : 0;
        return "T" + divider + marked + divider + get_content() ;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
