import java.time.LocalDate;

public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    public String toText() {
        return "T" + "|" + super.toText();
    };

    public LocalDate getDate() {
        return null;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
