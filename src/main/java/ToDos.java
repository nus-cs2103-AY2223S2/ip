public class ToDos extends Task {

    public ToDos(String s) {
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
