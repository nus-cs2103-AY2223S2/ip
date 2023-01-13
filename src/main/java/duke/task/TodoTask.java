package duke.task;

public class TodoTask extends GeneralDukeTask{
    public TodoTask(String info) {
        super(info, TaskType.TODO);
    }

    @Override
    public String storageString() {
        String status;
        if (this.getStatus()) {
            status = "[X] | ";
        } else {
            status = "[ ] | ";
        }
        return "[T] | " + status + this.getInformation();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
