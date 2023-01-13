public class Deadline extends Task{
    private EndTime endTime;
    public Deadline(String taskDescription, EndTime endTime) {
        super(taskDescription);
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()  + this.endTime.toString();
    }
}
