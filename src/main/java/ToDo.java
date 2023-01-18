public class ToDo extends Task {

    public ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        if (taskDone == false) {
            return "[T][ ] " + this.taskName;
        }
        return "[T][X] " + this.taskName;
    }

}
