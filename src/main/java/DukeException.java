public class DukeException extends Exception {

    public static void rethrow(String s) throws ToDoException, UnknownCommandException,
            ThirdException {
        try {
            if (s.equals("ToDoException"))
                throw new ToDoException();
            else if (s.equals("UnknownCommandException"))
                throw new UnknownCommandException();
            else
                throw new ThirdException("Third");
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

    static class ThirdException extends Exception {

        public ThirdException(String msg) {
            super(msg);
        }
    }
}
