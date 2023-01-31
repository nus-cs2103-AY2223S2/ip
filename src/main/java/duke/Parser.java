package duke;

/**
 * This class parses the command into an array
 * @author Bryan Ong
 */
public class Parser {

    public String[] parse(String command) {
        return command.split(" ");
    }

}
