import java.nio.file.Path;

public class TaskFactory {

    private static void validateName(String input) throws InvalidTaskException {
        if (input.trim().length() < 1) {
            throw new InvalidTaskException();
        }
    }

    private static String[] getNameDeadlinePair(String input) throws InvalidTaskException {
        int byIndex = input.indexOf("/by");
        if (byIndex < 1) {
            throw new InvalidTaskException();
        }
        String[] pair = new String[2];
        pair[0] = input.substring(0, byIndex - 1);
        validateName(pair[0]);
        pair[1] = input.substring(byIndex + 4);
        return pair;
    }

    private static String[] getNameStartEndTuple(String input) throws InvalidTaskException {
        int fromIndex = input.indexOf("/from");
        int byIndex = input.indexOf("/to");
        if (fromIndex < 1 || byIndex < 1) {
            throw new InvalidTaskException();
        }
        String[] tuple = new String[3];
        tuple[0] = input.substring(0, fromIndex - 1);
        validateName(tuple[0]);
        tuple[1] = input.substring(fromIndex + 6, byIndex - 1);
        tuple[2] = input.substring(byIndex + 4);
        return tuple;
    }
    public static Task parseCommand(String command, String information) throws InvalidTaskException {
        if (command.equals("TODO")) {
            validateName(information);
            return new Todo(information);
        } else if (command.equals("DEADLINE")) {
            String[] pair = getNameDeadlinePair(information);
            return new Deadline(pair[0], pair[1]);
        } else {
            String[] tuple = getNameStartEndTuple(information);
            return new Event(tuple[0], tuple[1], tuple[2]);
        }
    }

    public static Task parseLine(String[] directives) {
        if (directives[0].equals("T")) {
            return new Todo(directives[2], Boolean.parseBoolean(directives[1]));
        } else if (directives[0].equals("D")) {
            return new Deadline(directives[2], Boolean.parseBoolean(directives[1]), directives[3]);
        } else if (directives[0].equals("E")) {
            return new Event(directives[2], Boolean.parseBoolean(directives[1]), directives[3], directives[4]);
        }
        return null;
    }
}
