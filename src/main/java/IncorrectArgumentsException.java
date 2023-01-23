public class IncorrectArgumentsException extends DukeException {

    public IncorrectArgumentsException(String command, int numberExpected, int numberActual) {
        super("Sorry, command: " + command + " expected " + numberExpected + " arguments, detected " + numberActual
                + " arguments!");
    }

    @Override
    public String getExceptionType() {
        return "Incorrect Argument(s)";
    }
}
