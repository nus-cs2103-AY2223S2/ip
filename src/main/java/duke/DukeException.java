package duke;

class DukeException extends IllegalArgumentException {
    
    DukeException(String message) {
        super(message + " Cannot be empty");
    }

    @Override
    public String toString() {
        return String.format("Illegal Argument. %s cannot be empty", getMessage());
    }
}
