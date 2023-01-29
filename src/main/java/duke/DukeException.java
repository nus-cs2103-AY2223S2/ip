package duke;

public class DukeException extends Exception {

    public DukeException(String msg) {
        super(msg);
    }

    public static void rethrow(String s) throws ToDoException, UnknownCommandException {
        try {
            if (s.equals("ToDoException")) {
                throw new ToDoException();
            } else {
                throw new UnknownCommandException();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    static class ToDoException extends Exception {
        public ToDoException() {
            super("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        public ToDoException(String msg) {
            super(msg);
        }
    }

    static class UnknownCommandException extends Exception {
        public UnknownCommandException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        public UnknownCommandException(String msg) {
            super(msg);
        }
    }

}
