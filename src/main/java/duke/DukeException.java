package duke;

/**
 * This is DukeException which contains Duke's exceptions.
 */
public class DukeException extends Exception {

    public DukeException(String msg) {
        super(msg);
    }

    /**
     * Wrapper to rethrows errors.
     *
     * @param s Input string to rethrow.
     */
    public static void rethrow(String s) throws ToDoException, UnknownCommandException, CorruptedTaskListException {
        try {
            if (s.equals("ToDoException")) {
                throw new ToDoException();
            } else if (s.equals("UnkownCommandException")) {
                throw new UnknownCommandException();
            } else {
                throw new CorruptedTaskListException();
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

    static class CorruptedTaskListException extends Exception {
        public CorruptedTaskListException() {
            super("Task List is Corrupted!");
        }

        public CorruptedTaskListException(String msg) {
            super(msg);
        }
    }
}
