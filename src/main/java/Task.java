import javax.print.DocFlavor;

public class Task {
    private String name;
    private boolean status;

    public Task(String name) {
        this.status = false;
        this.name = name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getName() {
        return this.name;
    }
}
