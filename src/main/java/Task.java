public class Task {
    private String name;
    private boolean status;

    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status ? "X" : "";
    }

    @Override
    public String toString() {
        return this.name;
    }
}
