package duke.task;

public class TaskStub extends Task {
    public TaskStub() {
        super("description");
    }
    @Override
    public String getFileFormatString() {
        //to be split using "@"
        return "T" + "@" + "[]" + "@" + "description";
    }
}
