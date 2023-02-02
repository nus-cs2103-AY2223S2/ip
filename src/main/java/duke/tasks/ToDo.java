package duke.tasks;

public class ToDo extends Task {
    private static final String tag = "T";

    public ToDo(String description) {
        super(description);
    }


    public void ToDoReply(){
        String reply = "  ________________________________\n"
                + "  Got it. I've added this task:\n" + this.toString() + "\n"
                + "  Got it. I've added this task:\n"
                + "  ________________________________\n";
    }

    public String saveTask() {
        String completed = this.isDone? "1":"0";
        return this.tag + " | " + completed + " | "
                + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() +"\n";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}