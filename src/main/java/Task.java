import javax.print.DocFlavor;
import java.io.Serializable;

public class Task implements Serializable {
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

    public String toString() {
        return name;
    }
}
