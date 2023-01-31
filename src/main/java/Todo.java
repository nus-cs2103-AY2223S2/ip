import java.time.LocalDate;

public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    public String toText() {
        return "T" + "|" + getNameOfTask() + "|" + (isDone() ? 1 : 0);
    };

    public LocalDate getDate() {
        return null;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
