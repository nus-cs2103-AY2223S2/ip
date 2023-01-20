package duke;

class Todos extends Task {

    Todos(String description) {
        super(description);
    }
    
    Todos(String description, boolean isDone) {
        super(description, isDone);
    }
    
    @Override
    Todos markAsDone() {
        return new Todos(getDescription(), true);
    }

    Todos markAsUndone() {
        return new Todos(getDescription(), false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
