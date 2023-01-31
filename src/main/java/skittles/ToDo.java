package skittles;

//ToDo is one of 3 types of Task, so it must be a child of Task
public class ToDo extends Task {

    //constructor just use parent class Task's constructor
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        //making sure we add the "[T]"
        return "[T]" + super.toString();
    }

    @Override
    public String convertToText() {
        return String.format("T | %d | %s", super.getDoneOrNot() ? 1 : 0, super.getName() + System.lineSeparator());
    }
}
