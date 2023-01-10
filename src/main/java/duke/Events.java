package duke;

class Events extends Task {

    protected String from;
    protected String to;

    Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    Events(String description,String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }
    
    @Override
    Events markAsDone() {
        return new Events(this.getDescription(), from, to, true);
    }
    
    @Override
    Events markAsUndone() {
        return new Events(this.getDescription(), from, to, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + ")" + "(to: " + to + ")";
    }
}
