public class ToDo extends Task {

    public ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        if (!taskDone) {
            return "[T][ ] " + this.taskName;
        }
        return "[T][X] " + this.taskName;
    }

}
