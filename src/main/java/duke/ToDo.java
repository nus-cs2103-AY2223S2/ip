package duke;

class ToDo extends Task {
    String icon = "[T]";

    public ToDo(String details) {
        super(details);

    }

    @Override
    public String toString() {
        return icon + super.toString();
    }
}
