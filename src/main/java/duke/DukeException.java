package duke;

public class DukeException extends Exception {
    public String eMessage;

    public DukeException(String eMessage) {
        this.eMessage = eMessage;
    }
}

class unknownInputException extends DukeException {
    public unknownInputException() {
        super("Oh no, I am not sure what that means, could you try again?");
    }
}

class missingDescription extends DukeException {
    public missingDescription(String taskType) {
        super("Oh no, the description of a " + taskType + " cannot be empty! Please try again.");
    }
}

class missingNumber extends DukeException {
    public missingNumber(String operationType) {
        super("Oh no, the " + operationType + " must specific the task number! Please try again.");
    }
}

class taskNotExist extends DukeException {
    public taskNotExist() {
        super("Oh no, the task is not exist yet! Please try again.");
    }
}
