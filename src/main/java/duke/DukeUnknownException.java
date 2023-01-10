package duke;

class DukeUnknownException extends IllegalArgumentException {
    
    DukeUnknownException(String message) {
        super(message + " is not a legal command. I Do not know what it means");
    }
    /*
    @Override
    public String toString() {
        return String.format("Unknown action. %s is not a legal command", getMessage());
    }
    */

    @Override
    public String toString() {
        return String.format("Illegal Argument. Cannot be empty");
    }
}
