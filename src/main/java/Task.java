package duke.task;

public class Task {
    String name;
    boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public boolean isRelated(String name) {
        return this.name.contains(name);
    }

    @Override
    public String toString() {
        if (done) {
            return "1" + " | " + this.name;
        } else {
            return "0" + " | " + this.name;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof Task) {
            Task x = (Task) obj;
            if(this.name.equals(x.name)) {
                return true;
            }
        }
        return false;
    }
}
