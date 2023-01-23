public abstract class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    public abstract String getExceptionType();

    @Override
    public String toString() {
        return this.getExceptionType() + " detected! " + super.getMessage();
    }
}
