public class ToDo extends Task{
    public ToDo(String description) throws JamesException {
        super(description);
        if (description.isEmpty()) {
            throw new JamesException("OOPS!!! The description of a todo task cannot be empty.");
        }
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

