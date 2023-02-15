package rick;

import rick.command.Command;

/**
 * Represents the main class to receive commands, interact with the Storage,
 * and generate and return a UI string to output to the user.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class Rick {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private boolean isTimeToExit = false;

    /**
     * Constructs the Rick instance and populates it with the necessary
     * classes.
     */
    public Rick() {
        this.storage = Storage.create();
        this.ui = new Ui();
        this.tasks = TaskList.create(this.storage, this.ui);
    }

    /**
     * Generates and returns the server UI welcome message.
     *
     * @return The message.
     */
    public String getWelcome() {
        return ui.welcomeMessage();
    }

    /**
     * Processes the command and returns the server UI response.
     *
     * @param input The command from the user.
     * @return The UI response from the system.
     */
    public String getResponse(String input) {
        Parser.setTaskList(tasks);
        Command cmd = Parser.parse(input);
        isTimeToExit = cmd.isExit();
        return cmd.execute(tasks, ui);
    }

    /**
     * Generates and returns a boolean which indicates to the GUI if it
     * should exit.
     *
     * @return The indicative boolean.
     */
    public boolean isCloseAppTime() {
        return isTimeToExit;
    }
}
