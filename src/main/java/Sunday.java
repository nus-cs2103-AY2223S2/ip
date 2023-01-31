import command.Command;
import exceptions.SundayException;
import utilities.Parser;
import utilities.Ui;

/**
 * Sunday is the main class that runs the application.
 * It initializes the UI and runs the command loop.
 *
 * @author Tan Yan-Hao Joshua
 *
 */
public class Sunday {
    /**
     * The user interface instance
     */
    private Ui ui;

    /**
     * Constructs a new Sunday instance and initializes the UI.
     * The constructor also executes the INITIALIZE command with the given filepath.
     *
     * @param filepath The filepath used to initialize the application.
     */
    public Sunday(String filepath) {
        this.ui = new Ui();
        try {
            Command.INITIALIZE.execute(filepath);
        } catch (SundayException e) {
            Ui.printException(e);
        }
    }

    /**
     * Runs the command loop that reads and executes user commands.
     * The loop continues until the user enters the BYE command.
     */
    private void run() {
        Command command = null;
        while (command != Command.BYE) {
            try {
                String[] fullCommand = ui.readCommand();
                command = Parser.parse(fullCommand);
                command.execute(fullCommand[1]);
            } catch (SundayException e) {
                Ui.printException(e);
            }
        }
        this.ui.close();
    }

    /**
     * The main method that creates a new Sunday instance and starts the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Sunday("../data/sunday.txt").run();
    }
}
