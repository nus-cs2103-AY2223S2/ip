public class Task {
    private final String name;
    private String status;

    protected Task(String name) {
        this.name = name;
        this.status = " ";
    }

    protected Task(String name, String status) {
        this.name = name;
        this.status = status;
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

    @Override
    public String toString() {
        return "[" + this.status + "] " + this.name;
    }

    public String asTokens() {
        return this.status + ',' + this.name;
    }
}
