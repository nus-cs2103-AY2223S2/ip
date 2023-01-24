public class Deadline extends Task {

    private String deadline;
    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    @Override
    public String toStorageFormatString() {
        return "D|" + (isDone ? "1" : "0") + "|" + this.taskDescription + "|" + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")" ;
    }

}
