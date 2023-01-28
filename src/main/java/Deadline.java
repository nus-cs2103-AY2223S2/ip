public class Deadline extends Task{
    private String deadline;
    private String task;

    public Deadline(String task, String deadline) {
        super(task);
        this.task = task;
        this.deadline = deadline;
    }

    @Override
    public String getTask() {
        return this.task;
    }

    public String getDeadline() {
        return this.deadline;
    }
    
    @Override 
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.deadline + ")";
    }
}
