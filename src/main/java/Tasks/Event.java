package tasks;

import exceptions.NoTaskDescriptionException;

public class Event extends Task{
    String startTime;
    String endTime;

    protected Event(String name, String startTime, String endTime) throws NoTaskDescriptionException {
        super(name, "Event");
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString()
            + "( from: " + this.startTime 
            + ") ( to: " + this.endTime + ")";
    }

    @Override 
    protected String taskToSave() {
        return "E|" + super.taskToSave() + "|" + this.startTime + "|" + this.endTime;
    }
}
