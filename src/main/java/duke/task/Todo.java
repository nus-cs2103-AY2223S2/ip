package duke.task;

import duke.DukeException;

public class Todo extends Task{

    public static Todo create(String text) throws DukeException {
        if (text.length() < 1) {
            throw new DukeException();
        } else {
            return new Todo(text.substring(1));
        }
    }

    public static Todo create(String text, Boolean isDone) throws DukeException{
        if (text.length() < 1) {
            throw new DukeException();
        } else {
            return new Todo(text.substring(1), isDone);
        }
    }

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getType() {
        return "todo";
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
