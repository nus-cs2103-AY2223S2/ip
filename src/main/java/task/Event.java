package task;

import time.Duration;

import java.time.LocalDate;


public class Event extends Task {

    private Duration duration;
    private static String taskType = "E";

    public Event(int isCompleted, String taskDescription, Duration duration) {
        super(taskDescription, isCompleted);
        this.duration = duration;
    }

    public boolean isOnSameDay(LocalDate ld) {
        return duration.isSameDay(ld);
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " " + this.duration.toString();
    }

    @Override
    public String formatForSave() {
        return taskType + "<>" +super.formatForSave() + "<>" + this.duration.formatForSave();
    }
}
