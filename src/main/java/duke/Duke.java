package duke;

import command.Command;
import storage.Storage;

import task.TaskList;
import ui.Parser;
import ui.TextUi;

import java.util.ArrayList;


/**
 * duke.Duke is the class that responds to user enquiry.
 * It has other objects such as UI and storage, and connected these components
 * to process user-input commands, recording, carry out actual operations, and respond
 * to users.
 */
public class Duke {
    protected final TextUi ui;
    protected final TaskList taskList;
    protected final String myName;
    protected final ArrayList<String> commandList;
    protected final String RECORD_DIR = "./data";
    protected final String RECORD_NAME = "/duke.txt";
    private final Storage storage;
    private final Parser parser;

    /**
     * Constructor
     */
    public Duke() {
        this.myName = "Duke";
        this.ui = new TextUi(myName);
        this.taskList = new TaskList();
        this.storage = new Storage(RECORD_DIR, RECORD_NAME);
        this.parser = new Parser();
        this.commandList = new ArrayList<>();
        loadRecord();
    }

    /**
     * The process that interacts with user
     */
    public void run() {
        ui.showWelcome();

        boolean isRunning = true;

        while (isRunning) {
            String inMsg = ui.getUserInput();
            try {
                isRunning = handleCommand(inMsg, false);
                if (isRunning) {
                    commandList.add(inMsg);
                }
            } catch (DukeException e) {
                ui.printStructuredString(e.toString());
            } catch (NumberFormatException e) {
                ui.printStructuredString("Please specify the index.");
            }
        }

        storage.saveToFile(getCommandListString());
    }

    /**
     * Returns the name of the robot
     *
     * @return the name
     */
    public String getName() {
        return myName;
    }

    /**
     * Load record file
     */
    public void loadRecord() {
        storage.loadRecordIfExists(commandList);
        for (String s : commandList) {
            try {
                handleCommand(s, true);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Handles the input string from the user. It checks whether the input is
     * some command and performs the corresponding operation.
     *
     * In case of invalid or incomplete command, it throws an error message
     * to the user and prompts for a new command.
     *
     * @param inMsg         the input message from the user
     * @param suppressPrint suppress print out message or not
     * @return whether the program continues or not
     * @throws DukeException when the command is unknown
     */
    public boolean handleCommand(String inMsg, boolean suppressPrint) throws DukeException {
        Command command = Parser.parseCommand(inMsg, suppressPrint);
        command.execute(taskList, ui);
        return !command.isExit();
    }

    /**
     * Returns the string representation of all history commands.
     * This is different from the string of all tasks.
     *
     * @return the string containing all commands
     */
    public String getCommandListString() {
        String string = "";
        for (String s : commandList) {
            string = string + s + "\n";
        }
        return string;
    }
}
