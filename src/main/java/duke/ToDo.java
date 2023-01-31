package duke;

public class ToDo extends Task {

    public ToDo(String taskDescription) {
        setTaskDescription(taskDescription);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
