public class IncorrectArgumentsException extends DukeException {

    public IncorrectArgumentsException(String command, int numberExpected, int numberActual) {
        super(String.format("Sorry, command: %s expected %s arguments, detected %s arguments!",
                command, numberExpected, numberActual));
    }

    @Override
    public String getExceptionType() {
        return "Incorrect Argument(s)";
    }
}
