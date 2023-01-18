package Tasks;

import Exceptions.NoTaskDescriptionException;

public class Deadline extends Task {
    private String endDate;

    protected Deadline(String name, String endDate) throws NoTaskDescriptionException{
        super(name, "Deadline");
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[D] " + this.TasktoString() + "( by: " + this.endDate + ")";
    }
}
