package task;

import time.EndTime;

import java.time.LocalDate;

public class Deadline extends Task {

    private EndTime endTime;

    public Deadline(String taskDescription, EndTime endTime) {
        super(taskDescription);
        this.endTime = endTime;
    }
    public boolean isOnSameDay(LocalDate ldt) {
        return endTime.isSameDay(ldt);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()  + this.endTime.toString();
    }
}
