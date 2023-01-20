public class Todo extends Duke.Task {
    public Todo(String content) {
        super(content.substring(5));
    }

    public String toString() {
        String sign = "";
        if (super.mark == false) {
            sign = " ";
        } else {
            sign = "X";
        }
        return ". [T][" + sign + "] " + super.content;
    }
}