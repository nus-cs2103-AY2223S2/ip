package duke;
class ToDo extends Task {
    public ToDo(String keyword, String message, Boolean completed) {
        super(keyword, message, completed);
    }

    @Override
    public String provideDetails() {
        return this.completed ? "[T]" + "[x] " + this.description
                : "[T]" + "[ ] " + this.description;
    }
}
