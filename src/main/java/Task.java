import java.util.List;
public class Task {
    protected final int taskNumber;
    protected final boolean taskStatus;
    protected final String task;
    protected final int totalNumOfTasks;

    Task(int taskNumber, boolean taskStatus, String task, int totalNumOfTasks) {
        this.taskNumber = taskNumber;
        this.taskStatus = taskStatus;
        this.task = task;
        this.totalNumOfTasks = totalNumOfTasks;
    }

    public void markAsDone() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Nice! I've marked this task as done:\n" +
                "\t  " + "[X]" + " " + this.task +
                "\n\t____________________________________________________________");
    }

    public void unmarkAsUndone() {
        System.out.println("\t____________________________________________________________" +
                "\n\t OK, I've marked this task as not done yet:\n" +
                "\t  " + "[ ]" + " " + this.task +
                "\n\t____________________________________________________________");
    }

    public void printDelete(List<Task> allTasks) {
        int allTaskSize = allTasks.size() - 1;
        System.out.println("\t____________________________________________________________" +
                "\n\t Noted. I've removed this task:" + "\n\t   " +
                this.getTaskStatus() + " " + this.task +
                "\n\t Now you have " + allTaskSize + " tasks in the list.");
    }

    public int getTaskNumber() {
        return this.taskNumber;
    }

    public String getTaskType() {
        return "";
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

    public String getDeadline() {
        return "";
    }

    public String getEventStartTime() {
        return "";
    }

    public String getEventEndTime() {
        return "";
    }
}
