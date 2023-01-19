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
    
    @Override
    public String toString() {
        return String.format("[%s] %s", this.status ? "X" : " ", this.name);
    }
}
