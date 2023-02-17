package exceptions;

import java.io.File;

/**
 * Represents the Exception class unique and created for Duke to handle certain unexpected and unwanted situations.
 *
 * @author MrTwit99
 * @since 2023-02-02
 */
public class DukeException {
    private static int expectedArgs = 1;

    /**
     * This method helps to check if a certain command to run has the necessary arguments supplied to it.
     * <p></p>
     * This method is also able to prompt for a secondary check for cases where blank spaces or blank texts
     * may cause an issue.
     *
     * @param hasToEvaluate Boolean value that indicates whether there's a need for secondary check.
     * @param command String message of the command / action input by users via CLI.
     * @param testPortion String array of the affected portion of the command that needs to be checked.
     * @throws IncorrectNoOfArgumentException When arguments supplied are insufficient.
     */
    public static void validate(boolean hasToEvaluate, String command,
                                String[] testPortion) throws IncorrectNoOfArgumentException {
        StringBuilder sb = new StringBuilder();
        if (hasToEvaluate) {
            expectedArgs = 2;
        } else {
            expectedArgs = 1;
        }
        if (testPortion.length != expectedArgs) {
            sb.append("\n    ____________________________________________________________________________________\n")
                    .append("     OOPS!!! You have provided incorrect number of arguments for the command '")
                    .append(command).append("'.\n     Please try again after checking!\n");
            sb.append("    ____________________________________________________________________________________\n");
            throw new IncorrectNoOfArgumentException(sb.toString());
        }
        if (hasToEvaluate) {
            validate(testPortion[1], command);
        }
    }

    /**
     * This method helps to check if a certain command has blank spaces or blank texts as arguments supplied to it.
     *
     * @param testText String that needs to be checked for blank space or blank text.
     * @param command String message of the command / action input by users via CLI.
     * @throws IncorrectNoOfArgumentException When arguments supplied are insufficient due to blank text / blank space.
     */
    public static void validate(String testText, String command) throws IncorrectNoOfArgumentException {
        // Checking for blank spaces
        StringBuilder sb = new StringBuilder();
        if ((testText.equals("")) || (testText.isBlank())) {
            sb.append("\n    ____________________________________________________________________________________\n")
                    .append("     OOPS!!! You have provided incorrect number of arguments for the command '")
                    .append(command).append("'.\n     Please try again after checking!\n");
            sb.append("    ____________________________________________________________________________________\n");
            throw new IncorrectNoOfArgumentException(sb.toString());
        }
    }

    /**
     * This method is used to throw the InvalidCommandException whenever a command input by user via CLI is invalid
     * or unsupported by Duke.
     *
     * @throws InvalidCommandException When command called by user is invalid or unsupported by Duke.
     */
    public static void validate2() throws InvalidCommandException {
        StringBuilder sb = new StringBuilder();
        sb.append("\n    ____________________________________________________________________________________\n")
                .append("     OOPS!!! This is an incorrect command!\n")
                .append("     Please try again with a valid command!\n");
        sb.append("    ____________________________________________________________________________________\n");
        throw new InvalidCommandException(sb.toString());
    }

    /**
     * This method helps to check if the directory for the file could be located.
     * <p></p>
     * If it cannot be found, this method will throw a FolderNotFoundException to aid in the creation of the directory
     * and the file.
     *
     * @param directory String message of the relative path to check if the directory exists
     * @throws FolderNotFoundException When the directory cannot be located.
     */
    public static void folderCheck(String directory) throws FolderNotFoundException {
        File tempFolder = new File(directory);
        if ((!tempFolder.isDirectory()) || (!tempFolder.exists())) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n    ____________________________________________________________________________________\n")
                    .append("     Folder '").append(directory).append("' cannot be found.\n")
                    .append("     A new folder '").append(directory).append("' has been created for you!\n")
                    .append("     A new file 'storage' for storing the tasks has been created for you as well!\n");
            sb.append("    ____________________________________________________________________________________\n");
            throw new FolderNotFoundException(sb.toString());
        }
    }
}
