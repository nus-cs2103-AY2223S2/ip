package utilities;
import command.Command;
import exceptions.SundayException;

public class Parser {
    public static Command parse(String[] fullCommand) throws SundayException {
        try {
            return Command.valueOf(fullCommand[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SundayException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
