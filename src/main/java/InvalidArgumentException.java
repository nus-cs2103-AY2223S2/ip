class InvalidArgumentException extends DukeException {

    public InvalidArgumentException(String command, int actual, int expects) {
        super(
            String.format(
                "%d argument(s), expected %d for command %s",
                actual,
                expects,
                command
            )
        );
    }

    @Override
    public String getExceptionName() {
        return "Invalid Argument(s)";
    }
}
