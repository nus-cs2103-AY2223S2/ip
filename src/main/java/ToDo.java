class ToDo extends Task {

    public ToDo(String message) {
        super(message);
    }

    @Override
    public String provideDetails() {
        return this.completed ? "[T]" + "[x] " + this.task
                : "[T]" + "[ ] " + this.task;
    }


}
