class DeadLine extends Task {
    DeadLine(String taskName) {
        super(taskName);
        this.tag = "deadline";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]");
        return sb.toString() + super.toString();
    }
}
