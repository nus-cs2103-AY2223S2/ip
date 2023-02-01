package duke.Tasks;

import java.io.Serializable;

public class Task implements Serializable {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        setDone(false);
    }

    public String getDescription() {
        return this.desc;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public boolean isDone() {   
        return this.isDone;   
    }

    public void toggleDoneOrNot() {
        if (this.isDone()) {
            setDone(false);
        } else {
            setDone(true);
        }
    }

    @Override
    public String toString() {
        String icon = isDone ? "[X]" : "[ ]";
        return icon + " " + this.getDescription();
    }
}
