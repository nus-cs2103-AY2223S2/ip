package duke.tasks;

public class Todo extends Task {

    static final String type  = "T";


    public Todo(String name) {
        super(name);
    }

    //getter for type

    public static String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
