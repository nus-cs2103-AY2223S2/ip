package tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public void ToDoReply(){
        String reply = "  ________________________________\n"
                + "  Got it. I've added this task:\n" + this.toString() + "\n"
                + "  Got it. I've added this task:\n"
                + "  ________________________________\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() +"\n";
    }
}