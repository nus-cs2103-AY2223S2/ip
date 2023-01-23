public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getData() {
        StringBuilder sb = new StringBuilder();
        sb.append("T | ");
        if (this.isDone) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(this.description).append("\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
