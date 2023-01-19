import java.util.Arrays;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

//    public String isolate() {
//        String str = this.description.replace("todo", "");
//        return str;
//    }

    @Override
    public String toString() {
        return " " + "[T]" + "[" + super.getStatusIcon() + "]" + super.toString();
    }
}
