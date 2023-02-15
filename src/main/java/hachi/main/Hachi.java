package hachi.main;

import hachi.commands.Command;

/**
 * Hachi, a virtual assistant, that process user instruction
 * and helps to store tasks and events.
 */
public class Hachi {
    static String separator = "‿୨♡୧‿‿‿‿୨♡୧‿‿‿‿୨♡୧‿";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Hachi constructuor.
     *
     * @param filePath The relative path to the file containing saved tasks.
     */
    public Hachi(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            this.tasks = new TaskList(storage.loadTaskList());
        } catch (Exception e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public String getWelcomeMessage() {
        return this.ui.welcomeMessage();
    }

    /**
     * Generates a response to the user instruction.
     *
     * @param input The user's input string.
     * @return A string of the response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (IllegalArgumentException e) {
            return separator + "\n" + "\n" + "Sorry I don't understand your command...";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

