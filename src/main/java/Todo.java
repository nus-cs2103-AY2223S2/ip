public class Todo extends Task {

    private Todo(String description, boolean isDone, String taskType) {
        super(description, isDone, taskType);
    }

    public Todo(String description) {
        this(description, false, "T");
    }

    public Task markTask() {
        System.out.println("Nice! I've marked this task as done:");
        Task markedTask = new Todo(super.description, true, super.taskType);
        System.out.println(markedTask);
        return markedTask;
    }

    public Task unmarkTask() {
        System.out.println("Ok, I've marked this task as not done yet:");
        Task unmarkedTask = new Todo(this.description, false, super.taskType);
        System.out.println(unmarkedTask);
        return unmarkedTask;
    }

    @Override
    public String toString() {
        return String.format("%s%s %s", super.getTaskTypeBox(), super.getStatusCheckbox(), super.toString());
    }
}
