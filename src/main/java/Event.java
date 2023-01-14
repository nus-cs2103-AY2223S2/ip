class Event extends Task {
    Event(String taskName) {
        super(taskName);
        this.tag = "event";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]");
        return sb.toString() + super.toString();
    }
}
