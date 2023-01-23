package sebastian.task;

import sebastian.time.EndTime;

import java.time.LocalDate;


public class Deadline extends Task {

    private EndTime endTime;
    private static final String taskType = "D";

    public Deadline(int isCompleted, String taskDescription, EndTime endTime) {
        super(taskDescription, isCompleted);
        this.endTime = endTime;
    }

    public boolean isOnSameDay(LocalDate ldt) {
        return endTime.isSameDay(ldt);
    }

    @Override
    public String toString() {
        return "["+ taskType +"]" + super.toString() + " " + this.endTime.toString();
    }

    @Override
    public String formatForSave() {
        return taskType + "<>" + super.formatForSave() + "<>" + endTime.formatForSave();
    }
}
