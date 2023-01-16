package duke.task;

/**
 * A TodoTask class that encapsulates the information a Todo Task.
 */
public class TodoTask extends DukeTask {
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
