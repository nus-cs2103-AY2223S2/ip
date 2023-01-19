public class ToDo extends Task{

    public ToDo(String description) throws ToDoDescriptionException{
        super(description);
        if (description.length() == 0) {
            throw new ToDoDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
