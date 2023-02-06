package rick;

import rick.command.Command;

/**
 * The main class to interface with the Rick server.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class Rick {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isTimeToExit = false;

    /**
     * Default constructor to set up the app.
     */
    public Rick() {
        this.storage = Storage.create();
        this.ui = new Ui();
        this.tasks = TaskList.create(this.storage, this.ui);
    }

    /**
     * Generates the server UI welcome message.
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
        Command cmd = Parser.parse(input);
        isTimeToExit = cmd.isExit();
        return cmd.execute(tasks, ui);
    }

    /**
     * Indicates to the GUI if it should exit.
     *
     * @return The indicative boolean.
     */
    public boolean isCloseAppTime() {
        return isTimeToExit;
    }
}
