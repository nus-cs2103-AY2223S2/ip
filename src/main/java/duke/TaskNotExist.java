package duke;

class TaskNotExist extends DukeException {
    public TaskNotExist() {
        super("Invalid value, there isn't this much tasks in the list :-( ");
    }
}
