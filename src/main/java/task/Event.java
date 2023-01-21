package task;

import time.Duration;

import java.time.LocalDate;

public class Event extends Task {
    private Duration duration;
    public Event(String taskDescription, Duration duration) {
        super(taskDescription);
        this.duration = duration;
    }
    public boolean isOnSameDay(LocalDate ld) {
        return duration.isSameDay(ld);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + this.duration.toString();
    }
}
