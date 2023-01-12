package duke.task;

public class TodoTask extends GeneralDukeTask{
    public TodoTask(String info) {
        super(info, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
