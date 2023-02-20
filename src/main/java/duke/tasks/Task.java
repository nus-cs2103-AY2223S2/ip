package duke.tasks;

import duke.exceptions.DukeExceptions;

public class Task {
    protected String description;
    protected String tag = " ";
    protected boolean isDone = false;

    public void formatDescription(String input) throws DukeExceptions {
        this.description = input;
    }
    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public boolean isMarked() { return this.isDone;}

    public String getTag() {return this.tag;}

    public String getDescription() {return this.description;}

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "[" + this.tag + "]" + "[" + mark + "] " + this.description;
    }
}
