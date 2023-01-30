package DukeHelpfulCode;

public class Todo extends Task{
    // tasks without any date/time atached to it
    public Todo(String name) {
        super(name);
    }

    public String toString(){
        return "[T] " + super.toString();
    }



}
