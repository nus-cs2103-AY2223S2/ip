public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj instanceof Todo) {
            Todo x = (Todo) obj;
            if(this.name.equals(x.name)) {
                return true;
            }
        }
        return false;
    }
}
