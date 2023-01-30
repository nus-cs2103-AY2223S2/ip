package duke.util;

public class Task {
    String nature;
    boolean isDone;
    String action;
    public Task (String nature, String action) {
        this.nature = nature;
        this.isDone = false;
        this.action = action;
    }

    public Task markDone() {
        this.isDone = true;
        return this;
    }

    public Task unMark() {
        this.isDone = false;
        return this;
    }

    public String getAdditionalInfo () {
        return "";
    }

    @Override
    public String toString() {
        String toPrintOut = "";
        toPrintOut += "[" + this.nature + "]";
        if (this.isDone) {
            toPrintOut += "[X] ";
        } else {
            toPrintOut += "[ ] ";
        }
        toPrintOut += this.action;
        return toPrintOut;
    }
}