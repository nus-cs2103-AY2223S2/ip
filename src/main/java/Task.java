public class Task {
    private final int taskNumber;
    private final boolean taskStatus;
    private final String task;

    Task(int taskNumber, boolean taskStatus, String task) {
        this.taskNumber = taskNumber;
        this.taskStatus = taskStatus;
        this.task = task;
    }

    public void markAsDone() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Nice! I've marked this task as done:\n" +
                "\t  [X]" + " " + this.task +
                "\n\t____________________________________________________________");
    }

    public void unmarkAsUndone() {
        System.out.println("\t____________________________________________________________" +
                "\n\t OK, I've marked this task as not done yet:\n" +
                "\t  [ ]" + " " + this.task +
                "\n\t____________________________________________________________");
    }

    public String getTaskStatus() {
        if (!this.taskStatus) {
            return "[ ]";
        } else {
            return "[X]";
        }
    }

    public String getTask() {
        return this.task;
    }
}
