package DukeBot;

import java.lang.reflect.Type;

public abstract class Task {

    public boolean completed;
    public String details;
    public Types type;

    public Task(String task) {
        this.completed = false;
        this.details = task;
    }

    public abstract String status();

    public void complete() {
        this.completed = true;
    }

    public void incomplete() {
        this.completed = false;
    }
}
