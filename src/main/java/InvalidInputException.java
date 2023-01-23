public class InvalidInputException extends DukeException {

    public InvalidInputException(String input) {
        super("Sorry, the input " + input + " is not a supported command!");
    }

    @Override
    public String getExceptionType() {
        return "Invalid Input(s)";
    }
}
