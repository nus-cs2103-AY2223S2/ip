package DukeHelpfulCode.Tasks;

public class Todo extends Task{
    // tasks without any date/time atached to it
    public Todo(String name) {
        super(name, "todo");
    }
    public Todo(String name, boolean isDone) {
        super(name, "todo", isDone);
    }

    public String toString(){
        return "[T] " + super.toString();
    }



}
