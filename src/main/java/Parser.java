public class Parser {

    public static Command parse(String command) throws DukeException {
        if (!Command.contains(command)) {
            throw new DukeException("Sorry! I don't know what that means!");
        }
        return Command.valueOf(command);
    }
}
