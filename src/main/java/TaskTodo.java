import java.time.LocalDate;

/**
 * The class representing a {@code Todo} task.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class TaskTodo extends DukeTask{
    public TaskTodo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toDBSchema() {
        return String.format(
                "%s|%s",
                "T",
                super.toDBSchema()
        );
    }

    @Override
    public boolean isOnDate(LocalDate dt) {
        return false;
    }
}
