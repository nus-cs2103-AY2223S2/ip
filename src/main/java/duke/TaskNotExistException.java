package duke;

public class TaskNotExistException extends DukeException{
    TaskNotExistException() {}

    @Override
    public String toString() {
        return "Sorry, There is no such task for this command\n";
    }
}
