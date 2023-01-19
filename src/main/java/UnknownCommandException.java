class UnknownCommandException extends DukeException {

    public UnknownCommandException(String cmd) {
        super(cmd);
    }

    @Override
    public String getExceptionName() {
        return "Unknown Command";
    }
}
