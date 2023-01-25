package duke.task;

import duke.utilities.Parser;

/**
 * The type To do.
 */
public class ToDo extends Task {

    public final String raw;

    /**
     * Instantiates a new To do.
     *
     * @param name the name
     * @param done the done
     */
    public ToDo(String name, boolean done) {
        super(name, done);
        raw = name;
    }

    @Override
    public void add() {
        message_add = Parser.add + Parser.todoUnmarked_spaced + task_name;
    }

    @Override
    public void display() {
        if (done) {
            message_display = Parser.todoMarked + task_name;
        } else {
            message_display = Parser.todoUnmarked + task_name;
        }

    }

    @Override
    public void delete() {
        if (done)
            message_delete = Parser.delete + Parser.todoMarked_spaced + task_name;
        else
            message_delete = Parser.delete + Parser.todoUnmarked_spaced + task_name;
    }

    @Override
    public void marked() {
        message_marked = Parser.mark + Parser.todoMarked_spaced + task_name;
        done = true;
    }

    @Override
    public void unmarked() {
        message_unmarked = Parser.unmark + Parser.todoUnmarked_spaced + task_name;
        done = false;
    }

}
