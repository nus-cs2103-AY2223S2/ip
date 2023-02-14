package duke.parser;

/**
 * This class deals with user inputs
 *
 * @author He Shuimei
 * @version 0
 */
public class Parser {
    /**
     * Returns an integer for different commands
     * @param command string input from user
     * @return int representing the command
     */
    public int parseCommand(String command) {
        switch(command) {
            case ("throwerr"):
                throw new RuntimeException();
            case ("bye"):
                return -1;
            case ("list"):
                return 1;
            case ("mark"):
                return 2;
            case ("unmark"):
                return 3;
            case ("delete"):
                return 4;
            case ("todo"):
                return 5;
            case ("deadline"):
                return 6;
            case ("event"):
                return 7;
            case("find"):
                return 8;
            default:
                return 0;
        }
    }
}
