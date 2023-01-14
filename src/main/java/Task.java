public class Task {
    private String task;
    private boolean completed = false;

    public Task(String task) {
        this.task = task;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public void setUncompleted() {
        this.completed = false;
    }


    @Override
    public String toString() {
        if (this.completed) {
            return "    [X] " + this.task;
        }
        return "    [ ] " + this.task;
    }
}
