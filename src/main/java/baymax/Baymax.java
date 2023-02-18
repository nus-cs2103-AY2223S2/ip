package baymax;

import exceptions.BaymaxException;

import commands.Command;


public class Baymax {
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser = new Parser();
    private static final Ui ui = new Ui();

    /**
     * Constructor for Baymax.
     */
    public Baymax() {
        Ui ui = new Ui();
        storage = new Storage("./data/Baymax.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (BaymaxException e) {
            ui.showLoadingErrorMessage();
            tasks = new TaskList();
        }
        ui.welcomeMessage();
    }

    /**
     * Runs the program.
     */
    public String run(String string) {
        Command command = parser.parse(string);
        return command.execute(tasks, ui, storage);
    }
}
