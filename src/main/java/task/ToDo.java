package task;
public class ToDo extends Task {

    /**
     * Constructor
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean marked) {
        super(description,marked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
