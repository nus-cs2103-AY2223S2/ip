package duke.Tasks;

import duke.Exceptions.DescriptionException;

public class ToDo extends Task {

    public ToDo(String description) throws DescriptionException {
        super(description);
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
