public class Tasks {
    protected static Task getInstance(String code, boolean status, String[] content) throws DukeException {
        if (code.equals("T")) {
            return new ToDos(status, content);
        } else if (code.equals("D")) {
            return new Deadlines(status, content);
        } else if (code.equals("E")) {
            return new Events(status, content);
        } else {
            throw new DukeException("Unsupported code");
        }
    }
}
