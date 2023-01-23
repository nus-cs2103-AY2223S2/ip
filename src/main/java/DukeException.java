public abstract class DukeException extends Exception{
    public DukeException(String message) {
        super("\t\u2639 OOPS!!! " + message);
    }

    public static class InvalidCommandException extends DukeException {
        public InvalidCommandException(String message) {
            super("Invalid command! " + message);
        }
    }

    public static class InvalidDateException extends DukeException {
        public InvalidDateException(String message) {
            super("Invalid date/time entered! " + message);
        }
    }
    public static class IndexOutOfBoundsException extends DukeException {
        public IndexOutOfBoundsException(String message) {
            super("Invalid index entered! " + message);
        }
    }
}
