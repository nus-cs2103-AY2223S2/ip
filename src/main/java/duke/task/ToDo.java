package duke.task;

public class ToDo extends Task {
    public ToDo(String desc, boolean done) {
        super(desc, done);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
