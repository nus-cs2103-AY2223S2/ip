public class ToDo extends Task {
    
    ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        String toReturn = "";
        if (this.done) {
            toReturn = "[T][X] " + this.name;
        } else {
            toReturn = "[T][ ] " + this.name;
        }
        return toReturn;
    }
}
