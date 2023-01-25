class Deadline extends Task {
    private final String byTime;

    public Deadline(String taskDescription, String byTime) {
        this.taskDescription = taskDescription;
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s by: %s", super.toString(), byTime);
    }
}
