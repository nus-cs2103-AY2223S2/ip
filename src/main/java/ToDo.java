public class ToDo extends Task {
    ToDo(String desc, boolean done) {
        super(desc, done);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
