package Duke.Tasks;

import Duke.TaskTable;
import Duke.Monitor;

public class Task {

    public boolean exited;
    protected boolean done;
    protected String desc;

    public Task(boolean done, String desc) {
        this.done = done;
        this.desc = desc;
    }

    public String showDesc() {
        return this.desc;
    }

    public String showIfDone() {
        if (this.done) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public Task mark() {
        this.done = true;
        return this;
    }

    public Task unmark() {
        this.done = false;
        return this;
    }

    @Override
    public String toString() {
        return this.showIfDone()  + this.desc;
    }

    public void run(TaskTable table, Monitor monitor) {

    };



}
