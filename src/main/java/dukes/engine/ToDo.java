package dukes.engine;

class ToDo extends Task {
    ToDo(String taskName) {
        super(taskName);
        this.tag = "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
