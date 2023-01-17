public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String ans = "[T]" + super.getStatusIcon() + super.getDescription();
        return ans;
    }
}
