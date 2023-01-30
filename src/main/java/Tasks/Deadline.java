package tasks;

import exceptions.NoTaskDescriptionException;

public class Deadline extends Task {
    private String endDate;

    protected Deadline(String name, String endDate) throws NoTaskDescriptionException{
        super(name, "Deadline");
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "( by: " + this.endDate + ")";
    }

    @Override
    protected String taskToSave() {
        return "D|" + super.taskToSave() + "|" + this.endDate;
    }
}
