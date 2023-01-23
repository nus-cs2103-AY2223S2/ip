package dukes.engine;

class ToDo extends Task {
    ToDo(String taskName) {
        super(taskName);
        this.tag = "T";
    }

    ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
        this.tag = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
