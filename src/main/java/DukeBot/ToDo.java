package DukeBot;

public class ToDo extends Task {

    private static final String typeToString = "[T]";

    public ToDo(String task) {
        super(task);
        this.type = Types.TODO;
    }

    @Override
    public String status() {
        String status = this.completed ? "[X]" : "[ ]";
        return typeToString + status + " " + details;
    }

}
