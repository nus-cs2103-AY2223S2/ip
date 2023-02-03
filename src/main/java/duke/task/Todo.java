package duke.task;

import duke.DukeException;

public class Todo extends Task {

    /**
     * Constructor for Todo task, loaded from the storage file.
     * @param input Description of the task.
     * @param isDone Whether the task is marked or unmarked.
     */
    public Todo(String input, boolean isDone) {
        super(input, isDone);
        this.symbol = 'T';
    }

    /**
     * Constructor for a new Todo task keyed in by the user.
     * @param input Description of the task, including its header "todo".
     * @throws DukeException If description of task is empty.
     */
    public Todo(String input) throws DukeException {
        super(input);
        this.symbol = 'T';
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1 || inputArr[1].isBlank()) {
            throw new DukeException("Sorry, the description of a todo cannot be empty!");
        }
        this.description = inputArr[1];
    }

    public String saveTask() {
        return this.symbol + "," + isDone + "," + this.description;
    }
}
