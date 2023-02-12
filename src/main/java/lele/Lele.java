package lele;

import java.io.IOException;

import lele.command.Command;
import lele.exception.LeleException;
import lele.parser.Parser;
import lele.storage.Storage;
import lele.task.TaskList;
import lele.ui.Ui;


/**
 * The main class that represents the chatbot, Lele, which helps
 * to build a checklist consisting of todos, deadlines, events.
 */
public class Lele {
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;


    /**
     * Constructor for Lele.
     *
     * @param filePath For Duke to find the path to an existing data.
     */
    public Lele(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (LeleException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }


    /**
     * Handles running the program with the respective components.
     * This function will catch checked exceptions thrown by the user.
     */
    public void run() {
        this.ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                String output = c.execute(taskList, ui, storage);
                System.out.println(output);
                isExit = c.isExit();
            } catch (LeleException | IOException e) {
                assert ui.showError(e.getMessage()) instanceof String : "Function should return a string";
                System.out.println(ui.showError(e.getMessage()));
            } finally {
                ui.showLine();
            }
        }
    }


    /**
     * The main method, initialises the program instance.
     *
     * @param args Takes in the command line argument.
     */
    public static void main(String[] args) {
        new Lele("./data/lele.txt").run();
    }

    /**
     * Receives user input from GUI and responds
     * accordingly with commands.
     *
     * @param input Query from user.
     * @return Response for user's query.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (LeleException | IOException e) {
            return ui.showError(e.getMessage());
        }
    }



}
