import java.util.*;

public class Todo extends Task {

    static final String type  = "T";


    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
