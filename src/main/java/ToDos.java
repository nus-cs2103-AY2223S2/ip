

public class ToDos extends Task {

    protected String icon = "[T]";

    public ToDos(String description, Boolean isDone) {
        super(description, 'T', isDone);
    }

    /**
     * Returns a string representation of this To-Do task
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}
