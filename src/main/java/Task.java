import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Task {
    public String title;
    private Boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public void tick() {
        this.isDone = true;
    }

    public void untick() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String checkBox = this.isDone ? "[X]" : "[ ]";
        return checkBox + " " + this.title;
    }

    public String toSavedString() {
        return (this.isDone ? "1" : "0") + "|" + this.title;
    }
}

