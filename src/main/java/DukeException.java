import java.util.*;
import java.io.*;

public class DukeException {
    static void validate (int actualArgs, int expectedArgs,String command) throws IncorrectNoOfArgumentException {
        StringBuilder sb = new StringBuilder();
        if (actualArgs != expectedArgs) {
            sb.append("\n    ____________________________________________________________\n")
                    .append("     ☹ OOPS!!! You have provided incorrect number of arguments for the command '")
                    .append(command).append("'.\n     Please try again after checking!\n")
                    .append("    ____________________________________________________________\n");
            throw new IncorrectNoOfArgumentException(sb.toString());
        }
    }

    static void validate (String text, String command) throws IncorrectNoOfArgumentException {
        StringBuilder sb = new StringBuilder();
        if ( (text.equals("")) || (text.isBlank()) ) {
            sb.append("\n    ____________________________________________________________\n")
                    .append("     ☹ OOPS!!! You have provided incorrect number of arguments for the command '")
                    .append(command).append("'.\n     Please try again after checking!\n")
                    .append("    ____________________________________________________________\n");
            throw new IncorrectNoOfArgumentException(sb.toString());
        }
    }

    static void validate2 () throws InvalidCommandException {
        StringBuilder sb = new StringBuilder();
        sb.append("    ____________________________________________________________\n")
                .append("     ☹ OOPS!!! This is an incorrect command!\n")
                .append("     Please try again with a valid command!\n")
                .append("    ____________________________________________________________\n");
        throw new InvalidCommandException(sb.toString());
    }
}

class IncorrectNoOfArgumentException extends Exception {
    public IncorrectNoOfArgumentException (String str) {
        super(str);
    }
}

class InvalidCommandException extends Exception {
    public InvalidCommandException (String str) {
        super(str);
    }
}
