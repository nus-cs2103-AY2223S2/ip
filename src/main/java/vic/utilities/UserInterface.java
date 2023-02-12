package vic.utilities;

import java.util.Scanner;

import vic.commands.ICommand;
import vic.exceptions.DukeException;
import vic.exceptions.InvalidCommandException;

/**
 * UserInterface class to interact with user
 * take in input from user and export output to user
 */
public class UserInterface {


    /**
     * Prints the content onto the display
     * in pre-define format
     *
     * @param sentences to print
     */
    public void speak(String... sentences) {
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
        String dialogSeparator = "____________________________________________________________";
        System.out.println(dialogSeparator);
    }

    /**
     * Asks user to input command
     *
     * @param sentence type of command to ask the user to input
     */
    public void getCommand(String sentence) {
        System.out.println(sentence);
    }

    /**
     * Prints the welcome message when first start up the Duke app
     */
    public void showWelcome() {
        String logo = "              \n"
                + "| | | | | |----  \n"
                + "| | | | | | |-- \n"
                + "| |_| | | | |--   \n"
                + " \\___/|_| |____\n";
        speak(logo, "Hello! I'm Vic", "What can I do for you?");
    }

    /**
     * Shows to error loading msg
     *
     * @param msg to be print
     */
    public void showLoadingError(String msg) {
        speak(msg);
    }

    /**
     * Shows to error msg
     *
     * @param msg to be print
     */
    public void showError(String msg) {
        speak(msg);
    }

    /**
     * Reads the user command
     *
     * @param taskManager hand over the command to task manager
     * @throws DukeException IF error occur
     */
    public ICommand readCommand(TaskManager taskManager) throws DukeException {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.next().trim();
        String content = sc.nextLine().trim();

        return ICommand.Type.valueOfOrElse(cmd.toUpperCase())
                .createCommand(new Parser(content, taskManager));
    }

    /**
     * Reads the user command
     *
     * @param taskManager hand over the command to task manager
     * @param input       from Gui
     * @throws DukeException IF error occur
     */
    public ICommand readCommand(TaskManager taskManager,
                                String input) throws DukeException {
        String[] cmd = input.split(" ", 2);
        if (cmd.length < 1) {
            throw new InvalidCommandException(input);
        }
        String content = "";
        if (cmd.length > 1) {
            content = cmd[1].trim();
        }

        return ICommand.Type.valueOfOrElse(cmd[0].toUpperCase())
                .createCommand(new Parser(content, taskManager));
    }
}
