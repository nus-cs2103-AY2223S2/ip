public class ToDo extends Task {
    public ToDo(String taskString) {
        super(taskString);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
