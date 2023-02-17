package duke.task;
import java.util.List;

public class Todo extends Task {
    private final String taskType;

    public Todo(int taskNumber, boolean taskStatus, String task, int totalNumOfTasks) {
        super(taskNumber, taskStatus, task, totalNumOfTasks);
        this.taskType = "[T]";
    }

    @Override
    public void markAsDone() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Nice! I've marked this task as done:\n" +
                "\t  " + this.taskType + "[X]" + " " + this.task +
                "\n\t____________________________________________________________");
    }

    @Override
    public void unmarkAsUndone() {
        System.out.println("\t____________________________________________________________" +
                "\n\t OK, I've marked this task as not done yet:\n" +
                "\t  " + this.taskType + "[ ]" + " " + this.task +
                "\n\t____________________________________________________________");
    }

    public void printToDoTask() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Got it. I've added this task:" +
                "\n\t   [T]" + super.getTaskStatus() + " " + super.task +
                "\n\t Now you have " + super.totalNumOfTasks + " tasks in the list." +
                "\n\t____________________________________________________________");
    }

    @Override
    public void printDelete(List<Task> allTasks) {
        int newTotalNumOfTasks = allTasks.size() - 1;
        System.out.println("\t____________________________________________________________" +
                "\n\t Noted. I've removed this task:" + "\n\t   " + this.taskType +
                super.getTaskStatus() + " " + super.task +
                "\n\t Now you have " + newTotalNumOfTasks + " tasks in the list." +
                "\n\t____________________________________________________________");
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }
}
