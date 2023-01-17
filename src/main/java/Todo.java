public class Todo extends Task{

    protected boolean isSet;
    public Todo (String description) {
        super(description);
        this.isSet = false;
    }


    public String getStatus() {
        return (isSet ? "T" : " "); // mark done task with T
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "]" + super.toString();
    }
}
