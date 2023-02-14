package crystal;


import crystal.command.Command;

/**
 * Represents the Crystal class.
 *
 */
public class Crystal {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private String filepath = "Crystal.txt";

    /**
     * Constructor for Crystal class.
     *
     */
    public Crystal() {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.readFileContents());
        } catch (CrystalException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    //@@author francisyzy-reused
    //Reused from https://github.com/francisyzy/ip/blob/master/src/main/java/duke/Duke.java
    // with minor modifications
    /**
     * Runs the program.
     * Shows the welcome message.
     * While isExit is false, loads the previous saved file and
     * takes in user commands for the list.
     *
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command cmd = Parser.parse(fullCommand);
                cmd.execute(tasks, ui, storage);
                storage.saveFile(tasks);
                isExit = cmd.isExit();
            } catch (CrystalException e) {
                ui.showError(e);

            }
        }
    }

    //@@author laihuiqi-reused
    //Reused from https://github.com/laihuiqi/ip/blob/master/src/main/java/chattime/Chattime.java
    /**
     * Returns the welcome message
     *
     */
    public String printWelcome() {
        return ui.showWelcome();
    }


    /**
     * Returns the bot response in the GUI
     * @param input the message from the user
     * @return the bot message
     */
    public String getResponse(String input) {
        String temp = "";
        try {
            Command c = parser.parse(input);
            temp = c.execute(tasks, ui, storage);
        } catch (CrystalException e) {
            return e.getMessage();
        }

        return temp;
    }


    /**
     * Main method which calls the run method.
     *
     */
    public static void main(String[] args) {
        new Crystal().run();
    }



}
