package duke.Task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String taskToData() {
        return String.format("[T] | %s | %s", this.getStatusIcon(), this.name);
    }

}
