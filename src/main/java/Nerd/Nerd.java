package Nerd;

import Nerd.Parser.Parser;
import Nerd.Ui.Ui;
import Nerd.entities.TaskList;
import Nerd.storage.Storage;
import Nerd.Commands.*;


/**
 * Represents the Duke.Duke Chat bot.
 * Running a duke object loads data from the specified file into memory,
 * and exiting the program writes data to the hard disk.
 */
public class Nerd {
    private TaskList list;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Duke.Duke Constructor for initializing the Duke object.
     *
     * @param filePath of the storage
     */
    public Nerd(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            storage.connect();
            list = new TaskList(storage);
        } catch (Exception e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * duke.Duke Main method that runs the duke chatbot.
     *
     * @param args Arguments of the main function
     */
    public static void main(String[] args) {
        new Nerd("duke.txt");
    }

    /**
     * Method that gets the user response as the input from the GUI and returns the response.
     *
     * @param input User input from the gui.
     * @return A string to displayed on the gui.
     */
    public String getResponse(String input) {
        String output;
        try {
            Command command = parser.parseCommand(input);
            output = command.processCommand(this.list, this.ui);
            storage.save(this.list);
        } catch (Exception e) {
            return e.getMessage();
        }
        return output;
    }
}








