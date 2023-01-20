public class Deadline extends Task{

    private EndTime endTime;
    private static final String taskType = "D";

    public Deadline(int isCompleted, String taskDescription, EndTime endTime) {
        super(taskDescription, isCompleted);
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return "["+ taskType +"]" + super.toString()  + this.endTime.toString();
    }

    @Override
    public String formatForSave() {
        return taskType + "<>" + super.formatForSave() + "<>" + endTime.formatForSave();
    }
}
