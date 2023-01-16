package DukeBot;

public class Task {

    public boolean completed;
    public String task;

    public Task(String task) {
        this.completed = false;
        this.task = task;
    }

    public String status() {
        return this.completed ? "[X]" : "[ ]";
    }

    public void complete() {
        this.completed = true;
    }

    public void incomplete() {
        this.completed = false;
    }
}
