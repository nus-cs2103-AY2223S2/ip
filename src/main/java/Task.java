public class Task {
    protected String description;
    protected TaskStatus status;

    public enum TaskStatus {
        DONE,
        NOT_DONE,
        DOING;
    }


    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {
        switch(status) {
            case DONE:
                return "X";
            case NOT_DONE:
                return " ";
            case DOING:
                return "~";
            default:
                return "?";
        }
    }

    public void markDone() {
        this.status = TaskStatus.DONE;
    }

    public void markUndone() {
        this.status = TaskStatus.NOT_DONE;
    }

    public void markDoing() {
        this.status = TaskStatus.DOING;
    }
}
