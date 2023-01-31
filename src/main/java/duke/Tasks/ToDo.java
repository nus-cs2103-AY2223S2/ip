package duke.Tasks;

public class ToDo extends Task {
    private static final String FORMAT = "todo {task name}";
    public ToDo(String desc) {
        super(desc);
    }
    
    public static String showFormat() {
        return "Create a `todo` with: " + FORMAT;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
