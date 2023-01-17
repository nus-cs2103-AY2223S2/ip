public class Deadline extends Task{
    private final String deadline;

    private Deadline(String description, boolean isDone, String taskType, String deadline) {
        super(description, isDone, taskType);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline) {
        this(description, false, "D", deadline);
    }

    public Task markTask() {
        System.out.println("Nice! I've marked this task as done:");
        Task markedTask = new Deadline(super.description, true, super.taskType, this.deadline);
        System.out.println(markedTask);
        return markedTask;
    }

    public Task unmarkTask() {
        System.out.println("Ok, I've marked this task as not done yet:");
        Task unmarkedTask = new Deadline(super.description, false, super.taskType, this.deadline);
        System.out.println(unmarkedTask);
        return unmarkedTask;
    }

    @Override
    public String toString() {
        return String.format("%s%s %s (by: %s)", super.getTaskTypeBox(), super.getStatusCheckbox(),
                super.toString(), this.deadline);
    }
}
