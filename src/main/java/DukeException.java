import java.lang.StringBuilder;
import java.io.File;

public class DukeException {
    private static int expectedArgs = 1;
    static void validate(boolean secondaryCheck, String command, String[] text) throws IncorrectNoOfArgumentException {
        StringBuilder sb = new StringBuilder();
        if (secondaryCheck) {
            expectedArgs = 2;
        } else {
            expectedArgs = 1;
        }
        if (text.length != expectedArgs) {
            sb.append("\n    ____________________________________________________________\n")
                    .append("     ☹ OOPS!!! You have provided incorrect number of arguments for the command '")
                    .append(command).append("'.\n     Please try again after checking!\n")
                    .append("    ____________________________________________________________\n");
            throw new IncorrectNoOfArgumentException(sb.toString());
        }
        if (secondaryCheck) {
            validate(text[1], command);
        }
    }

    static void validate(String text, String command) throws IncorrectNoOfArgumentException {
        // Checking for blank spaces
        StringBuilder sb = new StringBuilder();
        if ( (text.equals("")) || (text.isBlank()) ) {
            sb.append("\n    ____________________________________________________________\n")
                    .append("     ☹ OOPS!!! You have provided incorrect number of arguments for the command '")
                    .append(command).append("'.\n     Please try again after checking!\n")
                    .append("    ____________________________________________________________\n");
            throw new IncorrectNoOfArgumentException(sb.toString());
        }
    }

    static void validate2() throws InvalidCommandException {
        StringBuilder sb = new StringBuilder();
        sb.append("\n    ____________________________________________________________\n")
                .append("     ☹ OOPS!!! This is an incorrect command!\n")
                .append("     Please try again with a valid command!\n")
                .append("    ____________________________________________________________\n");
        throw new InvalidCommandException(sb.toString());
    }

    static void folderCheck(String str) throws FolderNotFoundException {
        File tempFolder = new File(str);
        if ( (!tempFolder.isDirectory()) || (!tempFolder.exists()) ) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n    ____________________________________________________________\n")
                    .append("     Folder '").append(str).append("' cannot be found.\n")
                    .append("     Please download the latest version of Duke or create the folder '")
                    .append(str).append("' manually!\n")
                    .append("    ____________________________________________________________\n");
            throw new FolderNotFoundException(sb.toString());
        }
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

class FolderNotFoundException extends Exception {
    public FolderNotFoundException (String str) {
        super(str);
    }
}