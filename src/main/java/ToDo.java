class ToDo extends Task {
    ToDo(String taskName) {
        super(taskName);
        this.tag = "todo";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[T]");
        return sb.toString() + super.toString();
    }
}
