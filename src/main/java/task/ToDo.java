package task;


/** Tasks without any date/time attached to it. */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }


    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
