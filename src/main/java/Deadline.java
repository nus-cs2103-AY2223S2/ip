class Deadline extends Task {
    public Deadline(String name, String start) {
        super(name, start, null);
    }

    @Override
    public String getFileDesc() {
        return this.isDone
        ? "D|1|" + this.name + "|" + this.startDate
        : "D|0|" + this.name + "|" + this.startDate;
    }

    @Override
    public String toString() {
        return this.isDone
                ? "[D][X] " + this.name + " (by: " + this.startDate + ")"
                : "[D][ ] " + this.name + " (by: " + this.startDate + ")";
    }
}