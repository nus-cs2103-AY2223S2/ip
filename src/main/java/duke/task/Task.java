package duke.task;

public class Task {
    private final String name;
    private String status;
    private String tag;

    protected Task(String name) {
        this.name = name;
        this.status = " ";
        this.tag = "";
    }

    protected Task(String name, String status, String tag) {
        this.name = name;
        this.status = status;
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        if (this.tag.equals("")) {
            return "[" + this.status + "] " + this.name;
        }
        return "[" + this.status + "] " + this.name + " #" + this.tag;
    }

    public String asTokens() {
        return this.status + ',' + this.tag + ',' + this.name;
    }
}
